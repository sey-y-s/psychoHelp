package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.ChoixMultiple;

import java.util.List;

public interface ChoixMultipleService {
    List<ChoixMultiple> getAllChoix();


    ChoixMultiple getChoixById(int id);


    ChoixMultiple saveChoix(ChoixMultiple choix);


    ChoixMultiple updateChoix(int id, ChoixMultiple choix);


    void deleteChoix(int id);
}
