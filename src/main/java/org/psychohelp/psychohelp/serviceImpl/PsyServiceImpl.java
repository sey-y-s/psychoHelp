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
    public Optional<Psychologue> GetPsychologueById(int PsychologueId) {
        return psychologueRepository.findById(PsychologueId);
    }

    @Override
    public List<Psychologue> UpdateEtat(int PsyId, boolean etat) {
        Psychologue psy=psychologueRepository.findById(PsyId).get();
        if(etat!=null){
            psy.setEtat(etat);

        }
        return psychologueRepository.save(psy);
    }


    @Override
    public Psychologue updatePsychologue(Psychologue psychologue, int PsychologueId) {
        Psychologue psy = psychologueRepository.findById(PsychologueId).get();



       return psychologueRepository.save(psy);

    }
}