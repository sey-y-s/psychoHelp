package org.psychohelp.psychohelp.serviceImpl;


import org.psychohelp.psychohelp.dto.SeanceDTO;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.entity.Seance;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.repository.SeanceRepository;
import org.psychohelp.psychohelp.service.SeanceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }


    @Override
    public Seance getSeanceById(Long id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Séance " + id + "introuvable"));
    }

    @Override
    public Seance createSeance(SeanceDTO seance) {
        Citoyen c = new Citoyen(); c.setId(seance.getCitoyenId());
        Creneau cr = new Creneau(); cr.setId(seance.getCreneauId());
        Seance s = new  Seance();
        s.setDateRdv(seance.getDateRdv());
        s.setStatut(StatutRdvEnum.RESERVER);
        s.setCitoyen(c);
        s.setCreneau(cr);
        return seanceRepository.save(s);
    }

    @Override
    public Seance cancelSeance(Long id) {
        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Séance " + id + "introuvable"));
        seance.setStatut(StatutRdvEnum.ANNULER);
        return seanceRepository.save(seance);
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
    public List<Seance> getSeancesByPsy(Long psyId) {
        return seanceRepository.findByPsyId(psyId);
    }
}
