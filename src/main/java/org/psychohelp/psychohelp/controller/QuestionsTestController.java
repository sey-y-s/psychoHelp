package org.psychohelp.psychohelp.controller;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionsTestController {
    private final QuestionsTestService questionsTestService ;

    @GetMapping
    public List<QuestionsTest> getAllQuestions(){
        return questionsTestService.getallQuestions();
    }


    @GetMapping("/{id}")
    public Optional<QuestionsTest> getTestById(@PathVariable int id) {

        return questionsTestService.getQuestionsById(id);
    }

    @PostMapping
    public QuestionsTest saveQuestions(
            @RequestBody QuestionsTest Questions){

        return questionsTestService.saveQuestions(Questions);

    }



    @PutMapping("/{id}")
    public QuestionsTest updateQuestions(
            @PathVariable int id,
            @RequestBody QuestionsTest questions){


        return questionsTestService.updateQuestions(id, questions);

    }



    @DeleteMapping("/{id}")
    public void deleteQuestions(
            @PathVariable int id){

        questionsTestService.deleteQuestions(id);

    }
}
