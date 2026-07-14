package org.psychohelp.psychohelp.serviceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.dto.DateRdvPourCitoyen;
import org.psychohelp.psychohelp.dto.UpdateCreneauDTO;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.mapper.CreneauMapper;
import org.psychohelp.psychohelp.repository.CreneauRepository;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.repository.SeanceRepository;
import org.psychohelp.psychohelp.service.CreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreneauServiceImpl implements CreneauService {

    private final CreneauRepository cp;
    private final PsychologueRepository pp;
    private final CreneauMapper mapper;
    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    public CreneauResponseDTO creer(CreneauDTO dto, HttpSession session) {

        /*Psychologue psychologue = pp.findById(dto.getPsychologueId().intValue())
                .orElseThrow(() ->
                        new RuntimeException("Psychologue introuvable"));

         */
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        Psychologue psychologue=new Psychologue();
        psychologue.setId(utilisateur.getId());

        //Creneau creneau = mapper.toEntity(dto);
        Creneau creneau=new Creneau();
        creneau.setJours(dto.getJours());
        creneau.setHeureDebut(dto.getHeureDebut());
        creneau.setHeureFin(dto.getHeureFin());
        creneau.setStatut(dto.getStatut());

        creneau.setPsychologue(psychologue);

        Creneau saved = cp.save(creneau);

        return mapper.toDTO(saved);
    }

    @Override
    public List<CreneauResponseDTO> getAll() {

        return cp.findAll()
                .stream()
                .map(
                        creneau -> new CreneauResponseDTO(creneau.getId(),creneau.getJours(),creneau.getHeureDebut(),creneau.getHeureFin(),creneau.getStatut(),creneau.getPsychologue().getNom())
                )
                .toList();
    }

    @Override
    public CreneauResponseDTO getById(Long id) {

        Creneau creneau = cp.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Créneau introuvable"));

        return mapper.toDTO(creneau);
    }

    @Override
    public CreneauResponseDTO update(Long id, UpdateCreneauDTO dto) {

        Creneau creneau = cp.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Créneau introuvable"));

        creneau.setJours(dto.getJours());
        creneau.setHeureDebut(dto.getHeureDebut());
        creneau.setHeureFin(dto.getHeureFin());
        creneau.setStatut(dto.getStatut());

        return mapper.toDTO(cp.save(creneau));
    }

    @Override
    public void delete(Long id) {

        Creneau creneau = cp.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Créneau introuvable"));

        cp.delete(creneau);
    }

    @Override
    public List<CreneauResponseDTO> getDisponibles() {

        return cp.findByStatutTrue()
                .stream()
                .map(
                        creneau -> new CreneauResponseDTO(creneau.getId(),creneau.getJours(),creneau.getHeureDebut(),creneau.getHeureFin(),creneau.getStatut(),creneau.getPsychologue().getNom())

                )
                .toList();
    }

    @Override
    public List<CreneauResponseDTO> getDisponiblesByPsychologueId(Integer psychologueId) {

        List<CreneauResponseDTO> creneaux =
                cp.findByPsychologueIdAndStatutTrue(psychologueId)
                        .stream()
                        .map(
                                creneau -> new CreneauResponseDTO(creneau.getId(),creneau.getJours(),creneau.getHeureDebut(),creneau.getHeureFin(),creneau.getStatut(),creneau.getPsychologue().getNom())

                        )
                        .toList();

        if (creneaux.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aucun créneau disponible pour ce psychologue"
            );
        }

        return creneaux;
    }

    @Override
    public List<DateRdvPourCitoyen> getToutesLesDatesDisponibles(int psychologueId) {
        List<DateRdvPourCitoyen>listes=new ArrayList<DateRdvPourCitoyen>();
        for (Creneau creneau : cp.findByPsychologueIdAndStatutTrue(psychologueId)) {
            genererDisponibilites(creneau,listes);
        }
        return listes;
    }

    private void genererDisponibilites(Creneau creneau, List<DateRdvPourCitoyen> disponibilites) {

        LocalDate debut = LocalDate.now();
        LocalDate fin = debut.plusDays(30); // générer les 30 prochains jours


        for(LocalDate date = debut; !date.isAfter(fin); date = date.plusDays(1)) {


            if(date.getDayOfWeek().equals(convertirJour(creneau.getJours()))) {

                int reserve= seanceRepository.rdvDejaPris(date,creneau.getId());
                if(reserve==0){
                    DateRdvPourCitoyen dto = new DateRdvPourCitoyen();

                    dto.setDate(date);
                    dto.setHeureDebut(creneau.getHeureDebut());
                    dto.setHeureFin(creneau.getHeureFin());
                    dto.setCreneauId(creneau.getId());
                    dto.setJours(creneau.getJours());

                    dto.setPsyId(creneau.getPsychologue().getId());
                    dto.setNomPsychologue(creneau.getPsychologue().getNom());


                    disponibilites.add(dto);
                }



            }
        }
    }
    private DayOfWeek convertirJour(String jour) {

        return switch(jour.toUpperCase()) {

            case "LUNDI" -> DayOfWeek.MONDAY;
            case "MARDI" -> DayOfWeek.TUESDAY;
            case "MERCREDI" -> DayOfWeek.WEDNESDAY;
            case "JEUDI" -> DayOfWeek.THURSDAY;
            case "VENDREDI" -> DayOfWeek.FRIDAY;
            case "SAMEDI" -> DayOfWeek.SATURDAY;
            case "DIMANCHE" -> DayOfWeek.SUNDAY;

            default -> throw new RuntimeException("Jour invalide");
        };
    }

}