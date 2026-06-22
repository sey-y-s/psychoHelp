package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.QuestionsTest;

import java.util.List;

public interface QuestionsTestService {
    List<QuestionsTest> getallQuestions();
    QuestionsTest getQuestionsById(int id);
    QuestionsTest saveQuestions(QuestionsTest questionsTest);
    QuestionsTest updateQuestions(int id, QuestionsTest questionsTest) ;
    void deleteQuestions(int id);
}
