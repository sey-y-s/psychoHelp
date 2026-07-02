package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.TestRequestDTO;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.AdminService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@Tag(
        name = "Administrateur",
        description = "Gestion des categories, de la validation et l'annulation des psychologue ainsi que la validation et annullation des conseils publier par les administrateur"
)
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(
            summary = "Créer un administrateur",
            description = "Ajoute un nouveau administrateur"
    )
    @PostMapping
    public Admin ajouterAdmin(@RequestBody AdminDTO dto, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.ajouterAdmin(dto);
    }

    @Operation(
            summary = "Modifier un administrateur",
            description = "Modifie un administrateur"
    )

    @PutMapping("/{id}")
    public Admin modifierAdmin(@PathVariable Integer id, @RequestBody AdminDTO dto, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.modifierAdmin(id, dto);
    }

    @Operation(
            summary = "Récuperer  un administrateur",
            description = "Affiche un  administrateur par son id"
    )
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.getAdminById(id);
    }

    @Operation(
            summary = "Récuperer  la  liste des administrateurs",
            description = "Affiche tout les administrateurs "
    )

    @GetMapping
    public List<AdminResponseDTO> getAllAdmins(HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.getAllAdmins();
    }

    @Operation(
            summary = "Supprimer un administrateur",
            description = "Supprime un administrateur"
    )
    @DeleteMapping("/{id}")
    public void supprimerAdmin(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        adminService.supprimerAdmin(id);
    }

    @Operation(
            summary = "Valider  un conseil",
            description = "valider un conseil poster par un psychologue "
    )
    @PutMapping("/conseils/{id}/valider")
    public Conseil validerConseil(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.validerConseil(id);
    }

    @Operation(
            summary = "Annuler un conseil",
            description = "Annuler un conseil poster par un psychologue "
    )

    @PutMapping("/conseils/{id}/annuler")
    public Conseil annulerConseil(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.annulerConseil(id);
    }

    @Operation(
            summary = "Valider un psychologue",
            description = "Valider l'inscription d'un psychologue"
    )

    @PutMapping("/psychologues/{id}/valider")
    public Psychologue validerInscriptionPsy(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.validerInscriptionPsy(id);
    }

    @Operation(
            summary = "Annuler un psychologue",
            description = "Annuler l'inscription d'un psychologue "
    )

    @PutMapping("/psychologues/{id}/annuler")
    public Psychologue annulerInscriptionPsy(@PathVariable Integer id,HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.annulerInscriptionPsy(id);
    }



}
