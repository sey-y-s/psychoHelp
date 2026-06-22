package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionsTestServiceImpl implements QuestionsTestService {
    @Override
    public List<QuestionsTest> getallQuestions() {
        return List.of();
    }

    @Override
    public QuestionsTest getQuestionsById(int id) {
        return null;
    }

    @Override
    public QuestionsTest saveQuestions(QuestionsTest questionsTest) {
        return null;
    }

    @Override
    public QuestionsTest updateQuestions(int id, QuestionsTest questionsTest) {
        return null;
    }

    @Override
    public void deleteQuestions(int id) {

    }
}
