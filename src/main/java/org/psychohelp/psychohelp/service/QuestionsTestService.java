package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.QuestionResponseWithtest_id;
import org.psychohelp.psychohelp.dto.QuestionsTestReponseDTO;
import org.psychohelp.psychohelp.dto.QuestionsTestRequestDTO;
import org.psychohelp.psychohelp.dto.questionTestResquestForModifDto;
import org.psychohelp.psychohelp.entity.QuestionsTest;

import java.util.List;
import java.util.Optional;

public interface QuestionsTestService {
    List<QuestionsTestReponseDTO> getallQuestions();
    Optional<QuestionsTestReponseDTO> getQuestionsById(int id);
    QuestionsTestReponseDTO saveQuestions(QuestionsTestRequestDTO questionsTest, Integer test_id);
    QuestionsTestReponseDTO updateQuestions(int id, QuestionsTestRequestDTO questionsTest) ;
    void deleteQuestions(int id);
    public QuestionsTestReponseDTO updateQuestionsAvecTestId(int id, questionTestResquestForModifDto questionTestResquestForModifDto);
    public QuestionResponseWithtest_id getQuestionsByIdbesoin(int id);
    public QuestionsTestReponseDTO saveQuestionsmoussa(questionTestResquestForModifDto questionsTestsDTO);
}
