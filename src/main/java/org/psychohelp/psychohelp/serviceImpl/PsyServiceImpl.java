package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.service.PsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PsyServiceImpl implements PsyService {
    @Autowired
    private PsychologueRepository psychologueRepository;


    @Override
    public Psychologue savePsychologue(Psychologue psychologue) {
        return psychologueRepository.save(psychologue);
    }

    @Override
    public List<Psychologue> PSYCHOLOGUEList() {
        return psychologueRepository.findAll();
    }

    @Override
    public Psychologue GetPsychologueById(int psychologueId) {
        return psychologueRepository.findById(psychologueId)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));
    }
    @Override
    public Psychologue UpdateEtat(int psyId, Psychologue psychologue) {

        Psychologue psy = psychologueRepository.findById(psyId)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        psy.setEtat(psychologue.getEtat());

        return psychologueRepository.save(psy);
    }


    @Override
    public Psychologue updatePsychologue(Psychologue psychologue, int psychologueId) {

        Psychologue psy = psychologueRepository.findById(psychologueId)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        if (psychologue.getDescription() != null) {
            psy.setDescription(psychologue.getDescription());
        }

        if (psychologue.getCv_path() != null) {
            psy.setCv_path(psychologue.getCv_path());
        }

        if (psychologue.getDiplome_path() != null) {
            psy.setDiplome_path(psychologue.getDiplome_path());
        }

        if (psychologue.getEtat() != null) {
            psy.setEtat(psychologue.getEtat());
        }

        if (psychologue.getStatus() != null) {
            psy.setStatus(psychologue.getStatus());
        }

        if (psychologue.getSpecialite() != null) {
            psy.setSpecialite(psychologue.getSpecialite());
        }

        return psychologueRepository.save(psy);
    }
}