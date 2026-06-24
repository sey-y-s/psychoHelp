package org.psychohelp.psychohelp.controller;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin ajouterAdmin(@RequestBody AdminDTO dto) {
        return adminService.ajouterAdmin(dto);
    }

    @PutMapping("/{id}")
    public Admin modifierAdmin(
            @PathVariable Integer id,
            @RequestBody AdminDTO dto) {

        return adminService.modifierAdmin(id, dto);
    }


    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Integer id) {

        return adminService.getAdminById(id);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {

        return adminService.getAllAdmins();
    }

    @DeleteMapping("/{id}")
    public void supprimerAdmin(@PathVariable Integer id) {

        adminService.supprimerAdmin(id);
    }

    @PutMapping("/conseils/{id}/valider")
    public Conseil validerConseil(@PathVariable Integer id) {

        return adminService.validerConseil(id);
    }

    @PutMapping("/conseils/{id}/annuler")
    public Conseil annulerConseil(@PathVariable Integer id) {

        return adminService.annulerConseil(id);
    }

    @PutMapping("/psychologues/{id}/valider")
    public Psychologue validerInscriptionPsy(@PathVariable Integer id) {

        return adminService.validerInscriptionPsy(id);
    }

    @PutMapping("/psychologues/{id}/annuler")
    public Psychologue annulerInscriptionPsy(@PathVariable Integer id) {

        return adminService.annulerInscriptionPsy(id);
    }

}
