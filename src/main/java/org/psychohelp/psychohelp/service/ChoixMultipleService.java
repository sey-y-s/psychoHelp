package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.ChoixMultiplesReponseDTO;
import org.psychohelp.psychohelp.dto.ChoixMultiplesRequestDTO;
import org.psychohelp.psychohelp.entity.ChoixMultiple;

import java.util.List;
import java.util.Optional;

public interface ChoixMultipleService {
    List<ChoixMultiplesReponseDTO> getAllChoix();


    Optional<ChoixMultiplesReponseDTO> getChoixById(int id);


    ChoixMultiplesReponseDTO saveChoix(ChoixMultiplesRequestDTO choix, Integer question_id);


    ChoixMultiplesReponseDTO updateChoix(int id, ChoixMultiplesRequestDTO choix);


    void deleteChoix(int id);
}
