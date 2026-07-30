package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.ChoixMultiplesReponseDTO;
import org.psychohelp.psychohelp.dto.ChoixMultiplesRequestDTO;
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
@Tag(name = "Choix multiples", description = "Gestion des choix")
public class ChoixMultipleController {

    private final ChoixMultipleService choixService;

    @Operation(summary = "Récupérer tous les choix ou par question")
    @GetMapping
    public List<ChoixMultiplesReponseDTO> getAllChoix(
            @RequestParam(required = false) Integer question_id) {
        if (question_id != null) {
            return choixService.getChoixByQuestionId(question_id);
        }
        return choixService.getAllChoix();
    }

    @Operation(summary = "Récupérer un choix par ID")
    @GetMapping("/{id}")
    public Optional<ChoixMultiplesReponseDTO> getChoixById(@PathVariable int id) {
        return choixService.getChoixById(id);
    }

    @Operation(summary = "Créer un choix")
    @PostMapping
    public ChoixMultiplesReponseDTO saveChoix(
            @RequestBody ChoixMultiplesRequestDTO choix,
            @RequestParam Integer question_id,
            HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return choixService.saveChoix(choix, question_id);
    }

    @Operation(summary = "Modifier un choix")
    @PutMapping("/{id}")
    public ChoixMultiple updateChoix(
            @PathVariable int id,
            @RequestBody ChoixMultiplesRequestDTO choixDTO,
            HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        return choixService.updateChoix(id, choixDTO);
    }

    @Operation(summary = "Supprimer un choix")
    @DeleteMapping("/{id}")
    public void deleteChoix(@PathVariable int id, HttpSession session) {
        Session.verifierRole(session, RoleEnum.ADMIN);
        choixService.deleteChoix(id);
    }
}