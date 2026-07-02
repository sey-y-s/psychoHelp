package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.dto.AddPsyDto;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.UpdateEtatStatusDto;
import org.psychohelp.psychohelp.dto.UpdatePsyDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.repository.ConseilRepository;
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
    public PsychologueListeDto UpdateEtatStatus(int psyId, UpdateEtatStatusDto updateEtatStatusDto) {

        Psychologue psy = psychologueRepository.findById(psyId)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        psy.setEtat(updateEtatStatusDto.getEtat());
        psy.setStatus(updateEtatStatusDto.getStatus());
        Psychologue psychologue=psychologueRepository.save(psy);

           return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());

    }


    @Override
    public PsychologueListeDto updatePsychologue(UpdatePsyDto updatePsyDto, int psychologueId) {

        Psychologue psy = psychologueRepository.findById(psychologueId)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

            psy.setNom(updatePsyDto.getNom());
            psy.setMail(updatePsyDto.getMail());


            psy.setPrenom(updatePsyDto.getPrenom());

            psy.setTelephone(updatePsyDto.getTelephone());

            psy.setDescription(updatePsyDto.getNom());

            psy.setDescription(updatePsyDto.getDescription());


            psy.setCv_path(updatePsyDto.getCv_path());


            psy.setDiplome_path(updatePsyDto.getDiplome_path());






            Specialite specialite = new Specialite();

            specialite.setId(updatePsyDto.getIdSpecialite());
            psy.setSpecialite(specialite);


Psychologue psychologue =psychologueRepository.save(psy);
        return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());
    }

    @Override
    public List<Conseil> getConseilByPsy(int id) {
        return  psychologueRepository.getConseilByPsy(id);
    }

    @Override
    public List<Psychologue> getPsychologueValide() {
        return psychologueRepository.getPsychologueValide();
    }
}