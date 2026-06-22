package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.ChoixMultiple;

import java.util.List;
import java.util.Optional;

public interface ChoixMultipleService {
    List<ChoixMultiple> getAllChoix();


    Optional<ChoixMultiple> getChoixById(int id);


    ChoixMultiple saveChoix(ChoixMultiple choix);


    ChoixMultiple updateChoix(int id, ChoixMultiple choix);


    void deleteChoix(int id);
}
