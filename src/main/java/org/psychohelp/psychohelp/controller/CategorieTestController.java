package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.service.CategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(
        name = "Catégories de tests",
        description = "Gestion des catégories de tests psychologiques"
)
public class CategorieTestController {

    private final CategorieService categorieService;

    public CategorieTestController(
            CategorieService categorieService) {

        this.categorieService = categorieService;
    }  
    @Operation(
            summary = "Créer une catégorie",
            description = "Ajoute une nouvelle catégorie de test"
    )

    @PostMapping
    public ResponseEntity<CategorieTest>
    creerCategorie(
            @RequestBody CategorieTest categorie) {

        return new ResponseEntity<>(
                categorieService.creerCategorie(categorie),
                HttpStatus.CREATED
        );
    }
    @Operation(
            summary = "Lister toutes les catégories",
            description = "Retourne la liste complète des catégories de tests"
    )

    @GetMapping
    public ResponseEntity<List<CategorieTest>>
    obtenirToutesLesCategories() {

        return ResponseEntity.ok(
                categorieService
                        .obtenirToutesLesCategories()
        );
    }
    @Operation(
            summary = "Rechercher une catégorie",
            description = "Retourne une catégorie à partir de son identifiant"
    )

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
    @Operation(
            summary = "Supprimer une catégorie",
            description = "Supprime une catégorie à partir de son identifiant"
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    supprimerCategorie(
            @PathVariable Integer id) {

        categorieService.supprimerCategorie(id);

        return ResponseEntity.noContent().build();
    }
}