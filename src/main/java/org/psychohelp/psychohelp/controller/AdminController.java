package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.*;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.AdminService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController


@RequestMapping("/api/admins")
//  @CrossOrigin(origins = "http://localhost:4200")
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
    public AdminResponseDTO  ajouterAdmin(@RequestBody AdminDTO dto, HttpSession session) {
//        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.ajouterAdmin(dto);
    }

@Operation(
        summary = "Modifier un administrateur",
        description = "Modifie un administrateur"
)

    @PutMapping("/{id}")
    public AdminResponseDTO  modifierAdmin(@PathVariable Integer id, @RequestBody AdminDTO dto, HttpSession session) {
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
    public String  supprimerAdmin(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        adminService.supprimerAdmin(id);
        return "Administrateur supprimé avec succès.";

    }

    @Operation(
            summary = "Valider  un conseil",
            description = "valider un conseil poster par un psychologue "
    )
    @PutMapping("/conseils/{id}/valider")
    public ConseilAfficheDto validerConseil(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.validerConseil(id);
    }

    @Operation(
            summary = "Annuler un conseil",
            description = "Annuler un conseil poster par un psychologue "
    )

    @PutMapping("/conseils/{id}/annuler")
    public ConseilAfficheDto annulerConseil(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.annulerConseil(id);
    }

    @Operation(
            summary = "Valider un psychologue",
            description = "Valider l'inscription d'un psychologue"
    )

    @PutMapping("/psychologues/{id}/valider")
    public PsychologueListeDto validerInscriptionPsy(@PathVariable Integer id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.validerInscriptionPsy(id);
    }

    @Operation(
            summary = "Annuler l'inscription d'un psychologue",
            description = "Annuler un psychologue "
    )

    @PutMapping("/psychologues/{id}/annuler")
    public PsychologueListeDto annulerInscriptionPsy(@PathVariable Integer id,HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return adminService.annulerInscriptionPsy(id);
    }
    @Operation(
            summary = "Liste des psychologues en attente",
            description = "Retourne tous les psychologues en attente de validation"
    )
    @GetMapping("/psychologues/en-attente")
    public List<PsychologueListeDto> listerPsychologuesEnAttente(HttpSession session){

        Session.verifierRole(session, RoleEnum.ADMIN);

        return adminService.listerPsychologuesEnAttente();

    }


    //    @GetMapping("/")
//    public AdminResponseDTO getAdminById(HttpSession session) {
//        Session.verifierRole(session, RoleEnum.ADMIN);
//   Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
//    return new AdminResponseDTO(
//        utilisateur.getId(),
//        utilisateur.getNom(),
//        utilisateur.getPrenom(),
//        utilisateur.getMail(),
//        utilisateur.getTelephone(),
//        utilisateur.getRole().toString());
//
//    }

    //
//    @PutMapping("/")
//    public AdminResponseDTO  modifierAdmin( @RequestBody AdminDTO dto, HttpSession session) {
//        Session.verifierRole(session, RoleEnum.ADMIN);
//        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
//        return adminService.modifierAdmin(utilisateur.getId(),  dto);
//
//    }

}
