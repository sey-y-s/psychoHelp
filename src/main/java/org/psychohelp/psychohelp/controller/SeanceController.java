package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.SeanceDTO;
import org.psychohelp.psychohelp.dto.SeanceResponseDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.service.SeanceService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.web.bind.annotation.*;


import org.psychohelp.psychohelp.entity.Seance;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/seances")
@RequiredArgsConstructor

@Tag(name = "Séances de RDV", description = "API de gestion des rendez-vous entre CITOYEN et PSY")
public class SeanceController {

    private final SeanceService seanceService;

    @GetMapping
    @Operation(summary = "Récupérer la liste des séances", description = "Retourne toutes les séances enregistrées dans le système.")
    public List<Seance> getAll() {
        return seanceService.getAllSeances();
    }

    @GetMapping("/{id}")
    @Operation( summary = "Récupérer une séance par ID", description = "Retourne la séance avec l'ID mentionné.")
    @ApiResponse(responseCode = "200", description = "Séance récupérée avec succès")
    public Seance getSeanceById(@PathVariable Long id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return seanceService.getSeanceById(id);
    }

    @PostMapping
    @Operation(summary = "Créer une séance", description = "Ajoute une nouvelle séance de RDV dans le système.")
    @ApiResponse(responseCode = "201", description = "Séance créée avec succès")
    public SeanceDTO create(@RequestBody SeanceDTO seance, HttpSession session) {
        Session.verifierRole(session, RoleEnum.CITOYEN);
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return seanceService.createSeance(seance, utilisateur);
    }

    @PutMapping("/{id}/annuler")
    @Operation(summary = "Annuler une séance", description = "Marquer le statut d'une séance existante comme annulé.")
    @ApiResponse(responseCode = "200", description = "Séance annulée avec succès")
    public ResponseEntity<SeanceDTO> annuler(@PathVariable Long id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.CITOYEN, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return ResponseEntity.ok(seanceService.cancelSeance(id, utilisateur)
        );
    }

    // TODO : Notifier la seconde entité (citoyen OU psy) de l'annulation d'un rdv


//    @GetMapping("/citoyen/{citoyenId}")
//    @Operation(summary = "Lister les séances d'un citoyen", description = "Récupère l'historique complet des séances associées à un citoyen spécifique.")
//    @ApiResponse(responseCode = "200", description = "Liste des séances récupérée avec succès")
//    public List<Seance> getByCitoyen(@PathVariable Long citoyenId) {
//        return seanceService.getSeancesByCitoyen(citoyenId);
//    }

    @GetMapping("/statut")
    @Operation(summary = "Filtrer les rdv par statut", description = "Recherche toutes les séances correspondant à un statut précis (ex: RESERVE, ANNULE).")
    @ApiResponse(responseCode = "200", description = "Liste filtrée récupérée avec succès")
    public List<Seance> getByStatut(@RequestParam StatutRdvEnum statut, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return seanceService.getSeancesByStatut(statut);
    }

    @GetMapping("/mes-rdv")
    @Operation(summary = "Lister les rendez-vous du psychologue connecté", description = "Retourne l'agenda et l'historique des rendez-vous du psychologue connecté.")
    @ApiResponse(responseCode = "200", description = "Liste des rendez-vous récupérée avec succès")
    public List<SeanceResponseDTO> getMesRendezVous(HttpSession session) {
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return seanceService.getSeancesByPsy(utilisateur.getId());
    }

    @PutMapping("/{id}/confirmer")
    public ResponseEntity<SeanceDTO> confirmer(@PathVariable Long id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = Session.getUtilisateur(session);

        return ResponseEntity.ok(seanceService.confirmerSeance(id, utilisateur)

        );
    }
}