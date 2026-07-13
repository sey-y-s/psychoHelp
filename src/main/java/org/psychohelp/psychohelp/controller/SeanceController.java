package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.SeanceDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.service.SeanceService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.web.bind.annotation.*;


import org.psychohelp.psychohelp.entity.Seance;
import org.springframework.http.ResponseEntity;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")

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
    public Seance getSeanceById(Long id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return seanceService.getSeanceById(id);
    }

    @PostMapping
    @Operation(summary = "Créer une séance", description = "Ajoute une nouvelle séance de RDV dans le système.")
    @ApiResponse(responseCode = "201", description = "Séance créée avec succès")
    public SeanceDTO create(@RequestBody SeanceDTO seance, HttpSession session) {
       // Session.verifierRole(session, RoleEnum.CITOYEN);
        return seanceService.createSeance(seance);
    }

    @PutMapping("/{id}/annuler")
    @Operation(summary = "Annuler une séance", description = "Marquer le statut d'une séance existante comme annulé.")
    @ApiResponse(responseCode = "200", description = "Séance annulée avec succès")
    public ResponseEntity<SeanceDTO> annuler(@PathVariable Long id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.CITOYEN, RoleEnum.PSYCHOLOGUE);
        return ResponseEntity.ok(seanceService.cancelSeance(id));
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
    @Operation(summary = "Lister les rdv d'un psychologue", description = "Récupère l'agenda et l'historique des séances associées à un psychologue spécifique.")
    @ApiResponse(responseCode = "200", description = "Liste des séances du psy récupérée avec succès")
    public List<Seance> getByPsy(HttpSession session) {
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
        return seanceService.getSeancesByPsy(utilisateur.getId());
    }
}
