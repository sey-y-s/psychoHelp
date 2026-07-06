package org.psychohelp.psychohelp.serviceImpl;


import org.psychohelp.psychohelp.dto.SeanceDTO;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.entity.Seance;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.exceptions.BadRequestException;
import org.psychohelp.psychohelp.exceptions.NotFoundException;
import org.psychohelp.psychohelp.repository.CitoyenRepository;
import org.psychohelp.psychohelp.repository.CreneauRepository;
import org.psychohelp.psychohelp.repository.SeanceRepository;
import org.psychohelp.psychohelp.service.SeanceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final CitoyenRepository citoyenRepository;
    private final CreneauRepository creneauRepository;

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }


    @Override
    public Seance getSeanceById(Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Séance " + id + "introuvable"));
    }

    @Override
    public SeanceDTO createSeance(SeanceDTO seance) {
        Citoyen c = citoyenRepository.findById(seance.getCitoyenId())
                .orElseThrow(() -> new NotFoundException("Citoyen " + seance.getCitoyenId() + "introuvable"));

        Creneau cr = creneauRepository.findById(seance.getCreneauId())
                .orElseThrow(() -> new NotFoundException("Creneau " + seance.getCreneauId() + "introuvable"));

        String jourSeance = seance.getDateRdv()
                .getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.FRENCH)
                .toUpperCase();

        Boolean creneauIsActive = cr.getStatut();

        if(!(cr.getJours().toUpperCase().equals(jourSeance)) || !creneauIsActive) {
            throw new BadRequestException("Gros problème : ce créneau n'est pas disponible");
        }

        Seance s = new  Seance();
        s.setDateRdv(seance.getDateRdv());
        s.setStatut(StatutRdvEnum.RESERVER);
        s.setCitoyen(c);
        s.setCreneau(cr);
        seanceRepository.save(s);
        return mapSeanceToDTO(s);
    }

    @Override
    public SeanceDTO cancelSeance(Long id) {
        Seance s = seanceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Séance " + id + "introuvable"));
        s.setStatut(StatutRdvEnum.ANNULER);
        seanceRepository.save(s);
        return mapSeanceToDTO(s);
    }


    @Override
    public List<Seance> getSeancesByCitoyen(Long citoyenId) {
        return seanceRepository.findByCitoyenId(citoyenId);
    }

    @Override
    public List<Seance> getSeancesByStatut(StatutRdvEnum statut) {
        return seanceRepository.findByStatut(statut);
    }

    @Override
    public List<Seance> getSeancesByPsy(int psyId) {
        return seanceRepository.findByPsyId(psyId);
    }

    public SeanceDTO mapSeanceToDTO(Seance seance) {
        SeanceDTO seanceDTO = new SeanceDTO();
        seanceDTO.setDateRdv(seance.getDateRdv());
        seanceDTO.setStatut(seance.getStatut());
        seanceDTO.setCitoyenId(seance.getCitoyen().getId());
        seanceDTO.setCreneauId(seance.getCreneau().getId());
        return seanceDTO;
    }

}
