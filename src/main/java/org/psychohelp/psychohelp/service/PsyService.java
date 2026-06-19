package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Psychologue;

import java.util.List;
import java.util.Optional;

public interface PsyService {

    // ajout operation
    Psychologue savePsychologue(Psychologue psychologue);

    // lecture operation
    List<Psychologue> PSYCHOLOGUEList();
    Optional<Psychologue> GetPsychologueById(int PsychologueId);
    Psychologue UpdateEtat(int PsyId,Psychologue psychologue);


    // modification operation
    Psychologue updatePsychologue(Psychologue psychologue,int PsychologueId);


}
