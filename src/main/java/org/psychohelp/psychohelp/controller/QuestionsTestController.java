package org.psychohelp.psychohelp.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.QuestionsTestReponseDTO;
import org.psychohelp.psychohelp.dto.QuestionsTestRequestDTO;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/questions")
@AllArgsConstructor

@Tag(
        name = "Questions des tests",
        description = "Cette API permet de gérer les questions associées aux tests psychologiques. Elle permet d'ajouter de nouvelles questions, consulter les questions existantes, modifier leurs informations et les supprimer."
)
public class QuestionsTestController {


    private final QuestionsTestService questionsTestService;



    @Operation(
            summary = "Récupérer toutes les questions",
            description = "Retourne la liste de toutes les questions des tests psychologiques"
    )
    @GetMapping
    public List<QuestionsTestReponseDTO> getAllQuestions(){

        return questionsTestService.getallQuestions();
    }



    @Operation(
            summary = "Récupérer une question par ID",
            description = "Retourne une question spécifique selon son identifiant"
    )
    @GetMapping("/{id}")
    public Optional<QuestionsTestReponseDTO> getTestById(@PathVariable int id) {

        return questionsTestService.getQuestionsById(id);
    }



    @Operation(
            summary = "Créer une question",
            description = "Ajoute une nouvelle question associée à un test psychologique"
    )
    @PostMapping
    public QuestionsTestReponseDTO saveQuestions(
            @RequestBody QuestionsTestRequestDTO questionsTestsDTO, HttpSession session, Integer test_id){
        Session.verifierRole(session, RoleEnum.ADMIN);

        return questionsTestService.saveQuestions(questionsTestsDTO, test_id);

    }




    @Operation(
            summary = "Modifier une question",
            description = "Met à jour une question existante"
    )
    @PutMapping("/{id}")
    public QuestionsTestReponseDTO updateQuestions(
            @PathVariable int id,
            @RequestBody QuestionsTestRequestDTO questionsTestsDTO, HttpSession session){
            Session.verifierRole(session, RoleEnum.ADMIN);
           //questionsTestsDTO.setId(id);
        return questionsTestService. updateQuestions(id, questionsTestsDTO);

    }




    @Operation(
            summary = "Supprimer une question",
            description = "Supprime une question selon son identifiant"
    )
    @DeleteMapping("/{id}")
    public void deleteQuestions(
            @PathVariable int id, HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        questionsTestService.deleteQuestions(id);

    }
}