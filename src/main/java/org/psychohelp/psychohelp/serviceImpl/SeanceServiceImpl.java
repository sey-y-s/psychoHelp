package org.psychohelp.psychohelp.serviceImpl;


import org.psychohelp.psychohelp.dto.CitoyenRendezVousResponseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        if (dto.getDateRdv() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La date du rendez-vous est obligatoire."
            );
        }

        LocalDate aujourdHui = LocalDate.now();
        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime finCreneau = LocalDateTime.of(dto.getDateRdv(), creneau.getHeureFin());

        if (!creneau.getStatut()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ce créneau n'est plus disponible."
            );
        }
        if (dto.getDateRdv().isBefore(aujourdHui)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Impossible de réserver un créneau passé."
            );
        }
        if (!finCreneau.isAfter(maintenant)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Impossible de réserver un créneau dont l'heure est déjà passée."
            );
        }
        int dejaPris = seanceRepository.rdvDejaPris(dto.getDateRdv(), creneau.getId());
        if (dejaPris > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ce créneau est déjà réservé pour cette date."
            );
        }

        Seance seance = new Seance();
        seance.setDateRdv(dto.getDateRdv());
        seance.setStatut(StatutRdvEnum.RESERVER);
        seance.setCitoyen(citoyen);
        seance.setCreneau(creneau);
        seanceRepository.save(seance);
        notificationService.envoyer(
                creneau.getPsychologue(),
                "Nouvelle demande de rendez-vous",
                "Vous avez une nouvelle réservation de "
                        + citoyen.nomComplet()
                        + " pour le "
                        + seance.getDateRdv()
                        + " de "
                        + creneau.getHeureDebut()
                        + " à "
                        + creneau.getHeureFin()
                        + ".",
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
                    "Annulation d'un rendez-vous",
                    "Le rendez-vous prévu le " + seance.getDateRdv()
                            + " de " + seance.getCreneau().getHeureDebut()
                            + " à " + seance.getCreneau().getHeureFin()
                            + " a été annulé par "
                            + seance.getCitoyen().nomComplet() + ".",
                    TypeNotificationEnum.RENDEZ_VOUS
            );
        } else {
            notificationService.envoyer(
                    seance.getCitoyen(),
                    "Rendez-vous annulé",
                    "Votre rendez-vous prévu le " + seance.getDateRdv()
                            + " de " + seance.getCreneau().getHeureDebut()
                            + " à " + seance.getCreneau().getHeureFin()
                            + " a été annulé par votre psychologue. "
                            + "Nous vous invitons à choisir un autre créneau disponible afin de reprogrammer votre séance.",
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
        List<Seance> seances = seanceRepository.findByPsyId(psyId);
        seances.forEach(this::mettreAJourStatutSiPasse);
        return seances.stream()
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
                "Votre rendez-vous prévu le " + seance.getDateRdv()
                        + " de " + seance.getCreneau().getHeureDebut()
                        + " à " + seance.getCreneau().getHeureFin()
                        + " a été confirmé par votre psychologue. "
                        + "Nous vous remercions de vous présenter à l'heure prévue.",
                TypeNotificationEnum.RENDEZ_VOUS
        );
        SeanceDTO dto = new SeanceDTO();
        dto.setDateRdv(seance.getDateRdv());
        dto.setCreneauId(seance.getCreneau().getId());

        return dto;
    }

    private void mettreAJourStatutSiPasse(Seance seance) {
        if (seance.getStatut() != StatutRdvEnum.RESERVER && seance.getStatut() != StatutRdvEnum.CONFIRMER) {
            return;
        }
        if (seance.getDateRdv() == null || seance.getCreneau() == null || seance.getCreneau().getHeureFin() == null) {
            return;
        }
        LocalDateTime finRendezVous = LocalDateTime.of(seance.getDateRdv(), seance.getCreneau().getHeureFin());
        if (finRendezVous.isBefore(LocalDateTime.now())) {
            seance.setStatut(StatutRdvEnum.TERMINER);
            seanceRepository.save(seance);
        }
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

    @Override
    public List<CitoyenRendezVousResponseDTO> getSeancesByCitoyenConnecte(Long citoyenId) {
        return seanceRepository.findSeancesByCitoyenConnecte(citoyenId);
    }
}