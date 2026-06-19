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
    List<Psychologue> UpdateEtat(int PsyId,boolean etat);


    // modification operation
    Psychologue updatePsychologue(Psychologue psychologue,int PsychologueId);


}
