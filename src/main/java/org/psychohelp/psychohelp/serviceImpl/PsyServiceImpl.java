package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import org.psychohelp.psychohelp.controller.PsychologueController;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.DocumentType;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatusValidationPsy;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.repository.SpecialiteRepo;
import org.psychohelp.psychohelp.service.FileStorageService;
import org.psychohelp.psychohelp.service.PsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PsyServiceImpl implements PsyService {

    @Autowired
    private PsychologueRepository psychologueRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SpecialiteRepo specialiteRepository;



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
            psychologueListeDto.setDiplomePath("http://localhost:8080/api/psychologues/documents?path=" + psychologue.getDiplomePath());
            psychologueListeDto.setCvPath("http://localhost:8080/api/psychologues/documents?path=" + psychologue.getCvPath());
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
            psy.setCvPath(updatePsyDto.getCv_path());
            psy.setDiplomePath(updatePsyDto.getDiplome_path());

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
            psyReponseDto.setDiplomePath(psychologue.getDiplomePath());
            psyReponseDto.setCvPath(psychologue.getCvPath());

            resultatPsy.add(psyReponseDto);
        }

        return resultatPsy;

    }

    @Override
    public PsychologueListeDto inscrirePsychologue(AddPsyDto dto, MultipartFile cv, MultipartFile diplome) {
        Specialite specialite = specialiteRepository.findById(dto.getIdSpecialite())
                .orElseThrow(() ->
                        new RuntimeException("Spécialité introuvable."));

        Psychologue psychologue = new Psychologue();

        psychologue.setNom(dto.getNom());
        psychologue.setPrenom(dto.getPrenom());
        psychologue.setTelephone(dto.getTelephone());
        psychologue.setMail(dto.getMail());
        psychologue.setMotDePasse(dto.getMotDePasse());
        psychologue.setDescription(dto.getDescription());
        psychologue.setRole(RoleEnum.PSYCHOLOGUE);
        psychologue.setStatus(StatusValidationPsy.ENATTENTE);
        psychologue.setSpecialite(specialite);
        // Première sauvegarde pour obtenir l'identifiant
        psychologue = psychologueRepository.save(psychologue);
        String cvPath = null;
        String diplomePath = null;

        try {
            cvPath = fileStorageService.storePsychologueDocument(
                    cv,
                    psychologue.getId(),
                    DocumentType.CV
            );
            diplomePath = fileStorageService.storePsychologueDocument(
                    diplome,
                    psychologue.getId(),
                    DocumentType.DIPLOME
            );

            psychologue.setCvPath(cvPath);
            psychologue.setDiplomePath(diplomePath);
            psychologue = psychologueRepository.save(psychologue);
            return mapToDto(psychologue);
        } catch (Exception exception) {
            if (cvPath != null) {
                fileStorageService.delete(cvPath);
            }
            if (diplomePath != null) {
                fileStorageService.delete(diplomePath);
            }
            throw exception;

        }
    }

    private PsychologueListeDto mapToDto(Psychologue psychologue) {

        PsychologueListeDto dto = new PsychologueListeDto();

        dto.setId(psychologue.getId());
        dto.setNom(psychologue.getNom());
        dto.setPrenom(psychologue.getPrenom());
        dto.setTelephone(psychologue.getTelephone());
        dto.setMail(psychologue.getMail());
        dto.setRole(psychologue.getRole());
        dto.setDateCreation(psychologue.getDateCreation());
        dto.setStatus(psychologue.getStatus());
        dto.setDescription(psychologue.getDescription());
        dto.setDiplomePath("http://localhost:8080/api/psychologues/documents?path=" + psychologue.getDiplomePath());
        dto.setCvPath("http://localhost:8080/api/psychologues/documents?path=" + psychologue.getCvPath());
        dto.setEtat(psychologue.getEtat());
        dto.setSpecialite(psychologue.getSpecialite().getNom());

        return dto;
    }


}