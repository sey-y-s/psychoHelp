package org.psychohelp.psychohelp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.TestReponseDTO;
import org.psychohelp.psychohelp.dto.TestRequestDTO;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.TestService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tests")
@AllArgsConstructor

@Tag(
        name = "Tests psychologiques",
        description = "Cette API permet de créer, consulter, modifier et supprimer les tests psychologiques proposés aux citoyens. Elle permet aussi de récupérer les informations d'un test ainsi que ses différentes données associées."
)
public class TestController {


    private final TestService testService;

    @Operation(
            summary = "Récupérer tous les tests",
            description = "Retourne la liste de tous les tests psychologiques disponibles"
    )
    @GetMapping
    public List<TestReponseDTO> getAllTests() {
        return testService.getAllTests();
    }

    @Operation(
            summary = "Récupérer un test par son identifiant",
            description = "Retourne un test psychologique selon son ID"
    )
    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable int id) {

        return testService.getTestById(id);
    }



    @Operation(
            summary = "Créer un nouveau test",
            description = "Ajoute un nouveau test psychologique dans la base de données"
    )
    @PostMapping
    public TestReponseDTO saveTest(@RequestBody TestRequestDTO testDTO, HttpSession session, Integer categories_test_id) {
       // Test test = testService.saveTest(testDTO);
       // return ResponseEntity.ok("Test ajouté avec succès");
        Session.verifierRole(session, RoleEnum.ADMIN);
        return testService.saveTest(testDTO,categories_test_id);
    }


    @Operation(
            summary = "Modifier un test",
            description = "Met à jour les informations d'un test existant"
    )
    @PutMapping("/{id}")
    public TestReponseDTO updateTest(
            @PathVariable int id,
            @RequestBody TestRequestDTO testDTO, HttpSession session) {

        //Test test = testService.updateTest(id, testDTO);
        //return ResponseEntity.ok("Test modifié avec succès");
        Session.verifierRole(session, RoleEnum.ADMIN);
        return testService.updateTest(id, testDTO);
    }

    @Operation(
            summary = "Récupérer les tests d'une catégorie",
            description = "Retourne la liste de tous les tests psychologiques associés à une catégorie spécifique"
    )
    @GetMapping("/by-categorie/{categorieId}")
    public List<TestReponseDTO> getTestsByCategorie(@PathVariable int categorieId) {
        return testService.getTestsByCategorie(categorieId);
    }


    @Operation(
            summary = "Supprimer un test",
            description = "Supprime un test psychologique selon son identifiant"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable int id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        testService.deleteTest(id);
        return ResponseEntity.ok("Test supprimé avec succès");

    }

}