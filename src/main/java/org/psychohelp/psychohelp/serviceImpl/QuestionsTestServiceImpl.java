package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.QuestionsTestsDTO;
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
    public QuestionsTest saveQuestions(QuestionsTestsDTO questionsTestsDTO) {

        QuestionsTest questionsTest = new QuestionsTest();

        questionsTest.setQuestion(questionsTestsDTO.getQuestion());

        return questionsTestRepository.save(questionsTest);
    }

    @Override
    public QuestionsTest updateQuestions(int id, QuestionsTestsDTO questionsTestsDTO) {
        QuestionsTest questionsTest = questionsTestRepository.findById(id).
                orElseThrow(()-> new RuntimeException("La question non trouvée"));

        questionsTest.setQuestion(questionsTestsDTO.getQuestion());

        return questionsTestRepository.save(questionsTest);
    }

    @Override
    public void deleteQuestions(int id) {
       questionsTestRepository.deleteById(id);
    }
}
