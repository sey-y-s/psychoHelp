package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.ChoixMultiplesReponseDTO;
import org.psychohelp.psychohelp.dto.ChoixMultiplesRequestDTO;
import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.psychohelp.psychohelp.entity.QuestionsTest;
import org.psychohelp.psychohelp.repository.ChoixMultipleRepository;
import org.psychohelp.psychohelp.repository.QuestionsTestRepository;
import org.psychohelp.psychohelp.service.ChoixMultipleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChoixMultipleServiceImpl implements ChoixMultipleService {

    private final ChoixMultipleRepository choixRepository;
    private final QuestionsTestRepository questionsTestRepository;

    @Override
    public List<ChoixMultiplesReponseDTO> getAllChoix() {
        return choixRepository.findAll().stream()
                .map(choix -> new ChoixMultiplesReponseDTO(
                        choix.getId(),
                        choix.getChoix(),
                        choix.getScore(),
                        choix.getQuestionTest().getQuestion(),
                        choix.getQuestionTest().getId()  // ← MODIFIÉ pour inclure questionId
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ChoixMultiplesReponseDTO> getChoixById(int id) {
        return choixRepository.findById(id)
                .map(choix -> new ChoixMultiplesReponseDTO(
                        choix.getId(),
                        choix.getChoix(),
                        choix.getScore(),
                        choix.getQuestionTest().getQuestion(),
                        choix.getQuestionTest().getId()  // ← MODIFIÉ
                ));
    }

    @Override
    public ChoixMultiplesReponseDTO saveChoix(ChoixMultiplesRequestDTO choixMultiplesDTO, Integer question_id) {
        ChoixMultiple choixMultiple = new ChoixMultiple();
        QuestionsTest question = questionsTestRepository.findById(question_id)
                .orElseThrow(() -> new RuntimeException("Question non trouvé"));

        choixMultiple.setChoix(choixMultiplesDTO.getChoix());
        choixMultiple.setScore(choixMultiplesDTO.getScore());
        choixMultiple.setQuestionTest(question);
        choixRepository.save(choixMultiple);

        return new ChoixMultiplesReponseDTO(
                choixMultiple.getId(),
                choixMultiple.getChoix(),
                choixMultiple.getScore(),
                question.getQuestion(),
                question.getId()  // ← MODIFIÉ
        );
    }

    @Override
    public ChoixMultiple updateChoix(int id, ChoixMultiplesRequestDTO choixMultiplesDTO) {
        ChoixMultiple ancienChoix = choixRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Choix non trouvé"));

        ancienChoix.setChoix(choixMultiplesDTO.getChoix());
        ancienChoix.setScore(choixMultiplesDTO.getScore());

        return choixRepository.save(ancienChoix);
    }

    @Override
    public void deleteChoix(int id) {
        choixRepository.deleteById(id);
    }

    // ← NOUVELLE MÉTHODE
    @Override
    public List<ChoixMultiplesReponseDTO> getChoixByQuestionId(Integer questionId) {
        List<ChoixMultiple> choixList = choixRepository.findByQuestionTestId(questionId);
        return choixList.stream()
                .map(choix -> new ChoixMultiplesReponseDTO(
                        choix.getId(),
                        choix.getChoix(),
                        choix.getScore(),
                        choix.getQuestionTest().getQuestion(),
                        choix.getQuestionTest().getId()
                ))
                .collect(Collectors.toList());
    }
}