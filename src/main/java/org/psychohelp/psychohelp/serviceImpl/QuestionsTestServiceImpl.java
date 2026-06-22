package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.repository.QuestionsTestRepository;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionsTestServiceImpl implements QuestionsTestService {
    private QuestionsTestRepository questionsTestRepository ;
    @Override
    public List<QuestionsTest> getallQuestions() {

        //return List.of();
        return questionsTestRepository.findAll() ;
    }

    @Override
    public Optional<QuestionsTest> getQuestionsById(int id) {

        return questionsTestRepository.findById(id);
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
