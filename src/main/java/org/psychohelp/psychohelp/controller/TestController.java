package org.psychohelp.psychohelp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.TestDTO;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.service.TestService;
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
    public List<Test> getAllTests() {
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
    public Test saveTest(@RequestBody TestDTO testDTO) {

        return testService.saveTest(testDTO);
    }


    @Operation(
            summary = "Modifier un test",
            description = "Met à jour les informations d'un test existant"
    )
    @PutMapping("/{id}")
    public Test updateTest(
            @PathVariable int id,
            @RequestBody TestDTO testDTO) {

        testDTO.setId(id);

        return testService.updateTest(id, testDTO);
    }


    @Operation(
            summary = "Supprimer un test",
            description = "Supprime un test psychologique selon son identifiant"
    )
    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable int id) {

        testService.deleteTest(id);

    }

}