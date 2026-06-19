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
    public Psychologue UpdateEtat(int PsyId,Psychologue psychologue) {
        Psychologue psy=psychologueRepository.findById(PsyId).get();
        if(psychologue.isEtat()){
            psy.setEtat(psychologue.isEtat());

        }
        return psychologueRepository.save(psy);
    }


    @Override
    public Psychologue updatePsychologue(Psychologue psychologue, int PsychologueId) {
        Psychologue psy = psychologueRepository.findById(PsychologueId).get();
        if(psychologue.getDescription()!=null){
            psy.setDescription(psychologue.getDescription());
        }
        if (psychologue.getCv_path()!=null){
            psy.setCv_path(psychologue.getCv_path());
        }
        if (psychologue.getDiplome_path()!=null){
            psy.setDiplome_path(psychologue.getDiplome_path());
        }
        if (psychologue.isEtat()){
            psy.setEtat(psychologue.isEtat());
        }
        if (psychologue.isStatus()){
            psy.setStatus(psychologue.isStatus());
        }



       return psychologueRepository.save(psy);

    }
}