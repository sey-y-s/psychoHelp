package org.psychohelp.psychohelp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.ChoixMultiplesDTO;
import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.ChoixMultipleService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/choix")
@AllArgsConstructor

@Tag(
        name = "Choix multiples",
        description = "Cette API permet de gérer les différentes propositions de réponses liées aux questions des tests psychologiques. Elle permet de créer, consulter, modifier et supprimer les choix proposés aux utilisateurs lors du passage d'un test."
)
public class ChoixMultipleController {


    private final ChoixMultipleService choixService;



    @Operation(
            summary = "Récupérer tous les choix",
            description = "Retourne la liste de tous les choix multiples disponibles"
    )
    @GetMapping
    public List<ChoixMultiple> getAllChoix(){

        return choixService.getAllChoix();

    }



    @Operation(
            summary = "Récupérer un choix par ID",
            description = "Retourne un choix multiple selon son identifiant"
    )
    @GetMapping("/{id}")
    public Optional<ChoixMultiple> getChoixById(
            @PathVariable int id){

        return choixService.getChoixById(id);

    }



    @Operation(
            summary = "Créer un choix",
            description = "Ajoute un nouveau choix de réponse pour une question"
    )
    @PostMapping
    public ChoixMultiple saveChoix(
            @RequestBody ChoixMultiplesDTO choix, HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);

        return choixService.saveChoix(choix);

    }



    @Operation(
            summary = "Modifier un choix",
            description = "Met à jour un choix multiple existant"
    )
    @PutMapping("/{id}")
    public ChoixMultiple updateChoix(
            @PathVariable int id,
            @RequestBody ChoixMultiplesDTO choixDTO, HttpSession session){
           Session.verifierRole(session, RoleEnum.ADMIN);


        return choixService.updateChoix(id, choixDTO);

    }



    @Operation(
            summary = "Supprimer un choix",
            description = "Supprime un choix multiple selon son identifiant"
    )
    @DeleteMapping("/{id}")
    public void deleteChoix(
            @PathVariable int id, HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);

        choixService.deleteChoix(id);

    }
}