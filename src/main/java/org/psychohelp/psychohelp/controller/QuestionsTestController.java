package org.psychohelp.psychohelp.controller;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionsTestController {
    private final QuestionsTestService questionsTestService ;

    @GetMapping
    public List<QuestionsTest> getAllQuestions(){
        return questionsTestService.getallQuestions();
    }
}
