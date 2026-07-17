package org.psychohelp.psychohelp.serviceImpl;


import org.psychohelp.psychohelp.dto.SeanceDTO;
import org.psychohelp.psychohelp.dto.SeanceResponseDTO;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.entity.Seance;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.enumeration.TypeNotificationEnum;
import org.psychohelp.psychohelp.exceptions.NotFoundException;
import org.psychohelp.psychohelp.exceptions.UnauthorizedException;
import org.psychohelp.psychohelp.repository.CitoyenRepository;
import org.psychohelp.psychohelp.repository.CreneauRepository;
import org.psychohelp.psychohelp.repository.SeanceRepository;
import org.psychohelp.psychohelp.service.NotificationService;
import org.psychohelp.psychohelp.service.SeanceService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeanceServiceImpl implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final CreneauRepository creneauRepository;
    private final CitoyenRepository citoyenRepository;
    private final NotificationService notificationService;

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
    public SeanceDTO createSeance(SeanceDTO dto, Utilisateur utilisateurConnecte) {
        Citoyen citoyen = citoyenRepository.findById(utilisateurConnecte.getId())
                .orElseThrow(() -> new NotFoundException("Citoyen introuvable"));
        Creneau creneau = creneauRepository.findById(dto.getCreneauId())
                .orElseThrow(() -> new NotFoundException("Créneau introuvable"));

        Seance seance = new Seance();
        seance.setDateRdv(dto.getDateRdv());
        seance.setStatut(StatutRdvEnum.RESERVER);
        seance.setCitoyen(citoyen);
        seance.setCreneau(creneau);
        seanceRepository.save(seance);
        notificationService.envoyer(
                creneau.getPsychologue(),
                "Nouvelle réservation",
                citoyen.nomComplet() + " a réservé un rendez-vous.",
                TypeNotificationEnum.RENDEZ_VOUS
        );
        SeanceDTO seanceDTO = new SeanceDTO();
        seanceDTO.setDateRdv(seance.getDateRdv());
        seanceDTO.setCreneauId(creneau.getId());

        return seanceDTO;
    }

    @Override
    public SeanceDTO cancelSeance(Long id, Utilisateur utilisateurConnecte) {
        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Séance introuvable."));

        if (utilisateurConnecte.getRole() == RoleEnum.CITOYEN) {
            if (seance.getCitoyen().getId() != utilisateurConnecte.getId()) {
                throw new UnauthorizedException(
                        "Vous ne pouvez pas annuler ce rendez-vous."
                );
            }
        } else if (utilisateurConnecte.getRole() == RoleEnum.PSYCHOLOGUE) {

            if (seance.getCreneau()
                    .getPsychologue()
                    .getId() != utilisateurConnecte.getId()) {
                throw new UnauthorizedException(
                        "Vous ne pouvez pas annuler ce rendez-vous."
                );
            }
        }
        seance.setStatut(StatutRdvEnum.ANNULER);
        seanceRepository.save(seance);

        if (utilisateurConnecte.getRole() == RoleEnum.CITOYEN) {
            notificationService.envoyer(
                    seance.getCreneau().getPsychologue(),
                    "Rendez-vous annulé",
                    seance.getCitoyen().nomComplet()
                            + " a annulé le rendez-vous.",
                    TypeNotificationEnum.RENDEZ_VOUS
            );
        } else {
            notificationService.envoyer(
                    seance.getCitoyen(),
                    "Rendez-vous annulé",
                    "Le psychologue a annulé votre rendez-vous.",
                    TypeNotificationEnum.RENDEZ_VOUS
            );
        }
        SeanceDTO dto = new SeanceDTO();
        dto.setDateRdv(seance.getDateRdv());
        dto.setCreneauId(seance.getCreneau().getId());

        return dto;
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
    public List<SeanceResponseDTO> getSeancesByPsy(int psyId) {
        return seanceRepository.findByPsyId(psyId)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public SeanceDTO confirmerSeance(Long id, Utilisateur utilisateurConnecte) {

        Seance seance = seanceRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Séance introuvable"));

        if (seance.getCreneau()
                .getPsychologue()
                .getId() != utilisateurConnecte.getId()) {
            throw new UnauthorizedException(
                    "Vous ne pouvez pas confirmer cette séance."
            );
        }
        seance.setStatut(StatutRdvEnum.CONFIRMER);
        seanceRepository.save(seance);
        notificationService.envoyer(
                seance.getCitoyen(),
                "Rendez-vous confirmé",
                "Votre rendez-vous du "
                        + seance.getDateRdv()
                        + " a été confirmé.",

                TypeNotificationEnum.RENDEZ_VOUS
        );
        SeanceDTO dto = new SeanceDTO();
        dto.setDateRdv(seance.getDateRdv());
        dto.setCreneauId(seance.getCreneau().getId());

        return dto;
    }

    private SeanceResponseDTO toResponseDTO(Seance seance) {

        SeanceResponseDTO dto = new SeanceResponseDTO();

        dto.setId(seance.getId());
        dto.setDateRdv(seance.getDateRdv());
        dto.setStatut(seance.getStatut());

        dto.setNomCitoyen(seance.getCitoyen().getNom());
        dto.setPrenomCitoyen(seance.getCitoyen().getPrenom());

        dto.setJour(seance.getCreneau().getJours());
        dto.setHeureDebut(seance.getCreneau().getHeureDebut());
        dto.setHeureFin(seance.getCreneau().getHeureFin());

        return dto;
    }
}
