package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Psychologue;

import java.util.List;
import java.util.Optional;

public interface PsyService {

    // ajout operation
    Psychologue savePsychologue(Psychologue psychologue);

    // lecture operation
    List<Psychologue> PSYCHOLOGUEList();
    Psychologue UpdateEtat(int PsyId,Psychologue psychologue);
    public Psychologue GetPsychologueById(int psychologueId);


    // modification operation
    Psychologue updatePsychologue(Psychologue psychologue,int PsychologueId);


}
