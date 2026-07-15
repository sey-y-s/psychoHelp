package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.dto.DateRdvPourCitoyen;
import org.psychohelp.psychohelp.dto.UpdateCreneauDTO;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.CreneauService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.psychohelp.psychohelp.utils.Session;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creneaux")
@Tag(
        name = "Gestion des créneaux",
        description = "API de gestion des créneaux des psychologues"
)
public class CreneauController {

    private final CreneauService cs;

    @Operation(
            summary = "Créer un créneau",
            description = "Permet à un psychologue d'ajouter un nouveau créneau de disponibilité."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Créneau créé avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Psychologue introuvable"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreneauResponseDTO creer(@RequestBody CreneauDTO dto,
                                    HttpSession session) {
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);

        return cs.creer(dto,session);
    }

    @Operation(
            summary = "Lister tous les créneaux",
            description = "Retourne la liste complète des créneaux enregistrés dans le système."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des créneaux récupérée avec succès"
    )
    @GetMapping
    public List<CreneauResponseDTO> getAll() {
        return cs.getAll();
    }

    @Operation(
            summary = "Obtenir un créneau",
            description = "Retourne les informations d'un créneau à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Créneau trouvé"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Créneau introuvable"
            )
    })
    @GetMapping("/{id}")
    public CreneauResponseDTO getById(@PathVariable Long id) {
        return cs.getById(id);
    }

    @Operation(
            summary = "Modifier un créneau",
            description = "Met à jour les informations d'un créneau existant."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Créneau modifié avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Créneau introuvable"
            )
    })
    @PutMapping("/{id}")
    public CreneauResponseDTO update(
            @PathVariable Long id,
            @RequestBody UpdateCreneauDTO dto,
            HttpSession session) {

        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        return cs.update(id, dto);
    }

    @Operation(
            summary = "Supprimer un créneau",
            description = "Supprime un créneau existant à partir de son identifiant."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Créneau supprimé avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Créneau introuvable"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        cs.delete(id);
    }

    @Operation(
            summary = "Lister tous les créneaux disponibles",
            description = "Retourne la liste de tous les créneaux disponibles dans le système."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des créneaux disponibles récupérée avec succès"
    )
    @GetMapping("/disponibles")
    public List<CreneauResponseDTO> getDisponibles() {
        return cs.getDisponibles();
    }

    @Operation(
            summary = "Lister les créneaux disponibles d'un psychologue",
            description = "Retourne tous les créneaux disponibles associés au psychologue dont l'identifiant est fourni."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Liste des créneaux disponibles récupérée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Psychologue introuvable ou aucun créneau disponible"
            )
    })
    @GetMapping("/disponibles/{psychologueId}")
    public List<CreneauResponseDTO> getDisponiblesByPsychologueId(
            @PathVariable Integer psychologueId) {

        return cs.getDisponiblesByPsychologueId(psychologueId);
    }
    @GetMapping("/{id}/disponiblePourCitoyen")
    public List<DateRdvPourCitoyen> getDateRv(@PathVariable Integer id,HttpSession session) {
        Session.verifierRole(session, RoleEnum.CITOYEN);

        return cs.getToutesLesDatesDisponibles(id);
    }
}