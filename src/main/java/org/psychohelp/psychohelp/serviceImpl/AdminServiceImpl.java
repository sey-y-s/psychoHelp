package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.*;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.repository.AdminRepository;
import org.psychohelp.psychohelp.repository.ConseilRepository;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.service.AdminService;
import org.psychohelp.psychohelp.service.QuestionsTestService;
import org.psychohelp.psychohelp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ConseilRepository conseilRepository;

    @Autowired
    private PsychologueRepository psychologueRepository;

//    @Autowired
//    private TestService testService;
//
//    @Autowired
//    private QuestionsTestService questionsTestService;


    @Override
    public Admin ajouterAdmin(AdminDTO dto) {

        Admin admin = new Admin();

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setMail(dto.getMail());
        admin.setTelephone(dto.getTelephone());
        //admin.setMotDePasse(dto.getMotDePasse());

        admin.setRole(RoleEnum.ADMIN);
        admin.setDateCreation(LocalDate.now());

        return adminRepository.save(admin);
    }

    @Override
    public Admin modifierAdmin(Integer id, AdminDTO dto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setMail(dto.getMail());
        admin.setTelephone(dto.getTelephone());

        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            admin.setMotDePasse(dto.getMotDePasse());
        }

        admin.setRole(RoleEnum.ADMIN);

        return adminRepository.save(admin);
    }


    @Override
    public Admin getAdminById(Integer id) {

        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {

        return adminRepository.findAll()
                .stream()
                .map(admin -> {
                    AdminResponseDTO dto = new AdminResponseDTO();

                    dto.setId(admin.getId());
                    dto.setNom(admin.getNom());
                    dto.setPrenom(admin.getPrenom());
                    dto.setMail(admin.getMail());
                    dto.setTelephone(admin.getTelephone());
                    dto.setRole(admin.getRole().name());

                    return dto;
                })
                .toList();
    }
    @Override
    public void supprimerAdmin(Integer id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));

        adminRepository.delete(admin);
    }

    @Override
    public Conseil validerConseil(Integer conseilId) {

        Conseil conseil = conseilRepository.findById(conseilId)
                .orElseThrow(() ->
                        new RuntimeException("Conseil introuvable"));

        conseil.setStatus(true);

        return conseilRepository.save(conseil);
    }

    @Override
    public Conseil annulerConseil(Integer conseilId) {

        Conseil conseil = conseilRepository.findById(conseilId)
                .orElseThrow(() ->
                        new RuntimeException("Conseil introuvable"));

        conseil.setStatus(false);

        return conseilRepository.save(conseil);
    }

    @Override
    public Psychologue validerInscriptionPsy(Integer id) {

        Psychologue psychologue = psychologueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        psychologue.setEtat(true);

        return psychologueRepository.save(psychologue);
    }

    @Override
    public Psychologue annulerInscriptionPsy(Integer id) {

        Psychologue psychologue = psychologueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        psychologue.setEtat(false);

        return psychologueRepository.save(psychologue);
    }

//    @Override
//    public List<Test> getAllTests() {
//        return testService.getAllTests();
//    }
//
//    @Override
//    public Optional<Test> getTestById(Integer id) {
//        return testService.getTestById(id);
//    }
//
//    @Override
//    public Test saveTest(TestDTO testDTO) {
//        return testService.saveTest(testDTO);
//    }
//
//    @Override
//    public Test updateTest(Integer id, TestDTO test) {
//        return testService.updateTest(id, test);
//    }
//
//    @Override
//    public void deleteTest(Integer id) {
//        testService.deleteTest(id);
//    }
//
//    @Override
//    public List<QuestionsTest> getAllQuestions() {
//        return questionsTestService.getallQuestions();
//    }
//
//    @Override
//    public QuestionsTest getQuestionById(Integer id) {
//        return questionsTestService.getQuestionsById(id)
//                .orElseThrow(() -> new RuntimeException("Question introuvable"));
//    }
//
//    @Override
//    public QuestionsTest saveQuestion(QuestionsTestsDTO question) {
//        return questionsTestService.saveQuestions(question);
//    }
//
//    @Override
//    public QuestionsTest updateQuestion(Integer id, QuestionsTestsDTO question) {
//        return questionsTestService.updateQuestions(id, question);
//    }
//
//    @Override
//    public void deleteQuestion(Integer id) {
//        questionsTestService.deleteQuestions(id);
//    }
}
