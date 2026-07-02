package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.QuestionsTestReponseDTO;
import org.psychohelp.psychohelp.dto.QuestionsTestRequestDTO;
import org.psychohelp.psychohelp.dto.TestReponseDTO;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.repository.QuestionsTestRepository;
import org.psychohelp.psychohelp.repository.TestRepository;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionsTestServiceImpl implements QuestionsTestService {
    private QuestionsTestRepository questionsTestRepository ;
    private TestRepository testRepository ;
    @Override
    public List<QuestionsTestReponseDTO> getallQuestions() {
        //return List.of();
        return questionsTestRepository.findAll().stream().map(
                questions -> new QuestionsTestReponseDTO(questions.getId(), questions.getQuestion(), questions.getTest().getNom_test())
        ).toList();
    }

    @Override
    public Optional<QuestionsTestReponseDTO> getQuestionsById(int id) {

        return questionsTestRepository.findById(id).map(
                question -> new QuestionsTestReponseDTO(question.getId(), question.getQuestion(), question.getTest().getNom_test())
        );
    }

    @Override
    public QuestionsTestReponseDTO saveQuestions(QuestionsTestRequestDTO questionsTestsDTO, Integer test_id) {
        Test test = new Test();
        test.setId(test_id);
        Test test1 = testRepository.findById(test_id)
                .orElseThrow(()-> new  RuntimeException("Question de test non trouvé"));;

        QuestionsTest questionsTest = new QuestionsTest();

        questionsTest.setQuestion(questionsTestsDTO.getQuestion());
        questionsTest.setTest(test1);
        questionsTestRepository.save(questionsTest);
        return new QuestionsTestReponseDTO(questionsTest.getId(), questionsTest.getQuestion(), test1.getNom_test());
    }

    @Override
    public QuestionsTestReponseDTO updateQuestions(int id, QuestionsTestRequestDTO questionsTestsDTO) {
        QuestionsTest questionsTest = questionsTestRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Question non trouvée"));

        questionsTest.setQuestion(questionsTestsDTO.getQuestion());

        questionsTestRepository.save(questionsTest);
        return new QuestionsTestReponseDTO(questionsTest.getId(), questionsTest.getQuestion(), questionsTest.getTest().getNom_test());
    }

    @Override
    public void deleteQuestions(int id) {
       questionsTestRepository.deleteById(id);
    }
}
