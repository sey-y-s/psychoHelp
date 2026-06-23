package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.service.CategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieTestController {

    private final CategorieService categorieService;

    public CategorieTestController(
            CategorieService categorieService) {

        this.categorieService = categorieService;
    }

    @PostMapping
    public ResponseEntity<CategorieTest>
    creerCategorie(
            @RequestBody CategorieTest categorie) {

        return new ResponseEntity<>(
                categorieService.creerCategorie(categorie),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/api/categorie-tests")
    public ResponseEntity<List<CategorieTest>>
    obtenirToutesLesCategories() {

        return ResponseEntity.ok(
                categorieService
                        .obtenirToutesLesCategories()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieTest>
    obtenirCategorieParId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                categorieService
                        .obtenirCategorieParId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieTest>
    modifierCategorie(
            @PathVariable Integer id,
            @RequestBody CategorieTest categorie) {

        return ResponseEntity.ok(
                categorieService
                        .modifierCategorie(id, categorie)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    supprimerCategorie(
            @PathVariable Integer id) {

        categorieService.supprimerCategorie(id);

        return ResponseEntity.noContent().build();
    }
}