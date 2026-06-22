package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.QuestionsTest;

import java.util.List;
import java.util.Optional;

public interface QuestionsTestService {
    List<QuestionsTest> getallQuestions();
    Optional<QuestionsTest> getQuestionsById(int id);
    QuestionsTest saveQuestions(QuestionsTest questionsTest);
    QuestionsTest updateQuestions(int id, QuestionsTest questionsTest) ;
    void deleteQuestions(int id);
}
