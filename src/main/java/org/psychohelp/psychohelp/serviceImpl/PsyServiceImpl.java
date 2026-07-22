package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.controller.PsychologueController;
import org.psychohelp.psychohelp.dto.PsyReponseDto;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.UpdateEtatStatusDto;
import org.psychohelp.psychohelp.dto.UpdatePsyDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.enumeration.StatusValidationPsy;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.service.PsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PsyServiceImpl implements PsyService {
    @Autowired
    private PsychologueRepository psychologueRepository;



    @Override
    public Psychologue savePsychologue(Psychologue psychologue) {
        return psychologueRepository.save(psychologue);
    }

    @Override
    public List<PsychologueListeDto> PSYCHOLOGUEList() {

        List<Psychologue> psychologues = psychologueRepository.findAll();

        List<PsychologueListeDto> resultatPsy = new ArrayList<>();

        for (Psychologue psychologue : psychologues) {

            PsychologueListeDto psychologueListeDto = new PsychologueListeDto();
            psychologueListeDto.setId(psychologue.getId());
            psychologueListeDto.setNom(psychologue.getNom());
            psychologueListeDto.setPrenom(psychologue.getPrenom());
            psychologueListeDto.setMail(psychologue.getMail());
            psychologueListeDto.setTelephone(psychologue.getTelephone());
            psychologueListeDto.setDescription(psychologue.getDescription());
            psychologueListeDto.setDiplome_path(psychologue.getDiplome_path());
            psychologueListeDto.setCv_path(psychologue.getCv_path());
            psychologueListeDto.setRole(psychologue.getRole());
            psychologueListeDto.setDateCreation(psychologue.getDateCreation());
            psychologueListeDto.setEtat(psychologue.getEtat());
            psychologueListeDto.setStatus(psychologue.getStatus());
            psychologueListeDto.setSpecialite(psychologue.getSpecialite().getNom());
            resultatPsy.add(psychologueListeDto);

        }

        return resultatPsy;
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

           return PsychologueController.mapPsytoDto(psychologue);
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
        return PsychologueController.mapPsytoDto(psychologue);
    }

    @Override
    public List<Conseil> getConseilByPsy(int id) {
        return  psychologueRepository.getConseilByPsy(id);
    }

    @Override
    public List<PsyReponseDto> getPsychologueValide() {
        List<Psychologue> psychologues = psychologueRepository.getPsychologueValide();

        List<PsyReponseDto> resultatPsy = new ArrayList<>();

        for (Psychologue psychologue : psychologues) {

            PsyReponseDto psyReponseDto = new PsyReponseDto();

            psyReponseDto.setNom(psychologue.getNom());
            psyReponseDto.setPrenom(psychologue.getPrenom());
            psyReponseDto.setMail(psychologue.getMail());
            psyReponseDto.setTelephone(psychologue.getTelephone());
            psyReponseDto.setDescription(psychologue.getDescription());
            psyReponseDto.setDiplome_path(psychologue.getDiplome_path());
            psyReponseDto.setCv_path(psychologue.getCv_path());

            resultatPsy.add(psyReponseDto);
        }

        return resultatPsy;

    }


}