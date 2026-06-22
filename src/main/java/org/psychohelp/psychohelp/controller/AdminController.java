package org.psychohelp.psychohelp.controller;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
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

    @PutMapping("/conseils/{id}/valider")
    public Conseil validerConseil(@PathVariable Integer id) {

        return adminService.validerConseil(id);
    }

    @PutMapping("/conseils/{id}/annuler")
    public Conseil annulerConseil(@PathVariable Integer id) {

        return adminService.annulerConseil(id);
    }

    @PostMapping("/categories")
    public CategorieTest ajouterCategorie(
            @RequestBody CategorieTest categorie) {

        return adminService.ajouterCategorie(categorie);
    }

    @GetMapping("/categories")
    public List<CategorieTest> obtenirToutesLesCategories() {

        return adminService.obtenirToutesLesCategories();
    }

    @PutMapping("/categories/{id}")
    public CategorieTest modifierCategorie(
            @PathVariable Integer id,
            @RequestBody CategorieTest categorie) {

        return adminService.modifierCategorie(id, categorie);
    }

    @DeleteMapping("/categories/{id}")
    public void supprimerCategorie(
            @PathVariable Integer id) {

        adminService.supprimerCategorie(id);
    }



}
