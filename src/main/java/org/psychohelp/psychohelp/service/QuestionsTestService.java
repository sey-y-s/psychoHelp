package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.QuestionsTestsDTO;
import org.psychohelp.psychohelp.entity.QuestionsTest;

import java.util.List;
import java.util.Optional;

public interface QuestionsTestService {
    List<QuestionsTest> getallQuestions();
    Optional<QuestionsTest> getQuestionsById(int id);
    QuestionsTest saveQuestions(QuestionsTestsDTO questionsTest);
    QuestionsTest updateQuestions(int id, QuestionsTestsDTO questionsTest) ;
    void deleteQuestions(int id);
}
