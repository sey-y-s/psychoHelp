package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.AdminResponseDTO;
import org.psychohelp.psychohelp.dto.QuestionsTestsDTO;
import org.psychohelp.psychohelp.dto.TestDTO;
import org.psychohelp.psychohelp.entity.*;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin ajouterAdmin(AdminDTO dto);

    Admin modifierAdmin(Integer id, AdminDTO dto);

    Admin getAdminById(Integer id);

    List<AdminResponseDTO> getAllAdmins();

    void supprimerAdmin(Integer id);

    Conseil validerConseil(Integer conseilId);

    Conseil annulerConseil(Integer conseilId);

    Psychologue validerInscriptionPsy(Integer id);

    Psychologue annulerInscriptionPsy(Integer id);

//    List<Test> getAllTests();
//
//    Optional<Test> getTestById(Integer id);
//
//    Test saveTest(TestDTO testDTO);
//
//    Test updateTest(Integer id, TestDTO testDTO);
//
//    void deleteTest(Integer id);
//
//    List<QuestionsTest> getAllQuestions();
//
//    QuestionsTest getQuestionById(Integer id);
//
//    QuestionsTest saveQuestion(QuestionsTestsDTO question);
//
//    QuestionsTest updateQuestion(Integer id, QuestionsTestsDTO question);
//
//    void deleteQuestion(Integer id);

}
