package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.TestDTO;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Admin ajouterAdmin(@RequestBody AdminDTO dto) {
        return adminService.ajouterAdmin(dto);
    }

    @Operation(
            summary = "Modifier un administrateur",
            description = "Modifie un administrateur"
    )

    @PutMapping("/{id}")
    public Admin modifierAdmin(
            @PathVariable Integer id,
            @RequestBody AdminDTO dto) {

        return adminService.modifierAdmin(id, dto);
    }

    @Operation(
            summary = "Récuperer  un administrateur",
            description = "Affiche un  administrateur par son id"
    )
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {

        return adminService.getAdminById(id);
    }

    @Operation(
            summary = "Récuperer  la  liste des administrateurs",
            description = "Affiche tout les administrateurs "
    )

    @GetMapping
    public List<Admin> getAllAdmins() {

        return adminService.getAllAdmins();
    }

    @Operation(
            summary = "Supprimer un administrateur",
            description = "Supprime un administrateur"
    )
    @DeleteMapping("/{id}")
    public void supprimerAdmin(@PathVariable Integer id) {

        adminService.supprimerAdmin(id);
    }

    @Operation(
            summary = "Valider  un conseil",
            description = "valider un conseil poster par un psychologue "
    )
    @PutMapping("/conseils/{id}/valider")
    public Conseil validerConseil(@PathVariable Integer id) {

        return adminService.validerConseil(id);
    }

    @Operation(
            summary = "Annuler un conseil",
            description = "Annuler un conseil poster par un psychologue "
    )

    @PutMapping("/conseils/{id}/annuler")
    public Conseil annulerConseil(@PathVariable Integer id) {

        return adminService.annulerConseil(id);
    }

    @Operation(
            summary = "Valider un psychologue",
            description = "Valider l'inscription d'un psychologue"
    )

    @PutMapping("/psychologues/{id}/valider")
    public Psychologue validerInscriptionPsy(@PathVariable Integer id) {

        return adminService.validerInscriptionPsy(id);
    }

    @Operation(
            summary = "Annuler un psychologue",
            description = "Annuler l'inscription d'un psychologue "
    )

    @PutMapping("/psychologues/{id}/annuler")
    public Psychologue annulerInscriptionPsy(@PathVariable Integer id) {

        return adminService.annulerInscriptionPsy(id);
    }

    @Operation(
            summary = "Afficher les tests",
            description = "lister tout les test"
    )

    @GetMapping("/tests")
    public List<Test> getAllTests() {
        return adminService.getAllTests();
    }

    @Operation(
            summary = "Afficher un test",
            description = "lister un test á travers son id"
    )
    @GetMapping("/tests/{id}")
    public Optional<Test> getTestById(@PathVariable Integer id) {
        return adminService.getTestById(id);
    }

    @Operation(
            summary = "Ajouter un test",
            description = "Créer un test"
    )
    @PostMapping("/tests")
    public Test saveTest(@RequestBody TestDTO testDTO) {
        return adminService.saveTest(testDTO);
    }

    @Operation(
            summary = "Modifier un test",
            description = "Mettre a jour un test"
    )
    @PutMapping("/tests/{id}")
    public Test updateTest(@PathVariable Integer id,
                           @RequestBody TestDTO test) {
        return adminService.updateTest(id, test);
    }

    @Operation(
            summary = "Supprimer  un test",
            description = "supprimer un test déja existant"
    )
    @DeleteMapping("/tests/{id}")
    public void deleteTest(@PathVariable Integer id) {
        adminService.deleteTest(id);
    }

}
