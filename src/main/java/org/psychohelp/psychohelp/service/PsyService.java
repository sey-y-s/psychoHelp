package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.AddPsyDto;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.UpdateEtatStatusDto;
import org.psychohelp.psychohelp.dto.UpdatePsyDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PsyService {

    // ajout operation
    Psychologue savePsychologue(Psychologue psychologue);

    // lecture operation
    List<Psychologue> PSYCHOLOGUEList();
    PsychologueListeDto UpdateEtatStatus(int PsyId, UpdateEtatStatusDto updateEtatStatusDto);
    public Psychologue GetPsychologueById(int psychologueId);


    // modification operation
    PsychologueListeDto updatePsychologue(UpdatePsyDto updatePsyDto, int psychologueId);
    public List<Conseil> getConseilByPsy( int id);



}
