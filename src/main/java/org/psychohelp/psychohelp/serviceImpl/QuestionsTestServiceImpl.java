package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.exceptions.NotFoundException;
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
//        Test test = new Test();
//        test.setId(test_id);
        Test test = testRepository.findById(test_id)
                .orElseThrow(()-> new  RuntimeException("Question de test non trouvé"));;

        QuestionsTest questionsTest = new QuestionsTest();

        questionsTest.setQuestion(questionsTestsDTO.getQuestion());
        questionsTest.setTest(test);
        questionsTestRepository.save(questionsTest);

        return new QuestionsTestReponseDTO(
                questionsTest.getId(),
                questionsTest.getQuestion(),
                test.getNom_test());
    }

    @Override
    public QuestionsTestReponseDTO updateQuestions(int id, QuestionsTestRequestDTO questionsTestsDTO) {
        QuestionsTest questionsTest = questionsTestRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Question non trouvée"));

        questionsTest.setQuestion(questionsTestsDTO.getQuestion());
        questionsTestRepository.save(questionsTest);

        return new QuestionsTestReponseDTO(
                questionsTest.getId(),
                questionsTest.getQuestion(),
                questionsTest.getTest().getNom_test());
    }

    @Override
    public void deleteQuestions(int id) {

        questionsTestRepository.deleteById(id);
    }

    @Override
    public QuestionsTestReponseDTO updateQuestionsAvecTestId(int id, questionTestResquestForModifDto questionTestResquestForModifDto) {
        Test test=new Test();
        QuestionsTest questionsTest = questionsTestRepository.findById(id).
                orElseThrow(()-> new NotFoundException("Question non trouvée"));

        questionsTest.setQuestion(questionTestResquestForModifDto.getQuestion());
        test.setId(questionTestResquestForModifDto.getTest_id());
        questionsTest.setTest(test);
        questionsTestRepository.save(questionsTest);
        return new QuestionsTestReponseDTO(questionsTest.getId(), questionsTest.getQuestion(), questionsTest.getTest().getNom_test());
    }
    @Override
    public QuestionResponseWithtest_id getQuestionsByIdbesoin(int id) {
        QuestionsTest question=questionsTestRepository.findById(id).orElseThrow(()->new NotFoundException("cette question n'existe pas"));

        return  new QuestionResponseWithtest_id(question.getId(),question.getQuestion(), question.getTest().getId());

    }

    @Override
    public QuestionsTestReponseDTO saveQuestionsmoussa(questionTestResquestForModifDto questionsTestsDTO) {
        Test test = new Test();

        QuestionsTest questionsTest = new QuestionsTest();
        test.setId(questionsTestsDTO.getTest_id());
        questionsTest.setQuestion(questionsTestsDTO.getQuestion());
        questionsTest.setTest(test);
        QuestionsTest q=questionsTestRepository.save(questionsTest);
        return new QuestionsTestReponseDTO(questionsTest.getId(), questionsTest.getQuestion(), q.getTest().getNom_test());
    }


}
