package org.psychohelp.psychohelp.controller;

import jakarta.validation.Valid;
import org.psychohelp.psychohelp.dto.AddPsyDto;
import org.psychohelp.psychohelp.dto.ConseilDto;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.ConseilService;
import org.psychohelp.psychohelp.service.PsyService;
import org.psychohelp.psychohelp.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PsychologueController {
    @Autowired private PsyService psyService;
    @Autowired private SpecialiteService specialiteService;
   @Autowired private ConseilService conseilService;
    @PostMapping("/psychologues")
    public PsychologueListeDto savePsychologue(@Valid @RequestBody AddPsyDto addPsyDto){
       // psychologue.setDateCreation(LocalDate.now());
        Psychologue psychologue=new Psychologue();
        psychologue.setNom(addPsyDto.getNom());
        psychologue.setPrenom(addPsyDto.getPrenom());
        psychologue.setTelephone(addPsyDto.getTelephone());
        psychologue.setMail(addPsyDto.getMail());
        psychologue.setMotDePasse(addPsyDto.getMotDePasse());
        psychologue.setRole(RoleEnum.PSYCHOLOGUE);
        psychologue.setDescription(addPsyDto.getDescription());
        psychologue.setDiplome_path(addPsyDto.getDiplome_path());
        psychologue.setCv_path(addPsyDto.getCv_path());
        //psychologue.setEtat(addPsyDto.getEtat());
        //recupere la specialité à partir de l'id
        Specialite specialite=specialiteService.getSpecialite(addPsyDto.getIdSpecialite());
        psychologue.setSpecialite(specialite);
        Psychologue psychologuecreer=psyService.savePsychologue(psychologue);



        return new PsychologueListeDto(psychologuecreer.getId(),psychologuecreer.getNom(),psychologuecreer.getPrenom(),psychologuecreer.getTelephone(),psychologuecreer.getMail(),psychologuecreer.getRole(),psychologuecreer.getDateCreation(),psychologuecreer.getStatus(),psychologuecreer.getDescription(),psychologuecreer.getDiplome_path(),psychologuecreer.getCv_path(),psychologuecreer.getEtat());


    }
    @GetMapping("/psychologues")
    public List<PsychologueListeDto> psychologueList(){
        return psyService.PSYCHOLOGUEList().stream().map(
                psychologue -> new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat())
        ).toList();
    }
    @PutMapping("/psychologues/{id}")
    public Psychologue updatePsychologue( @RequestBody Psychologue psychologue ,@PathVariable("id") int PsychologueId){
        return psyService.updatePsychologue(psychologue,PsychologueId);
    }
    @PatchMapping("/psychologues/{id}/etat")
    public Psychologue updateEtat(
            @PathVariable int id,
            @RequestBody Psychologue psychologue){

        return psyService.UpdateEtat(id, psychologue);
    }
    @GetMapping("/psychologues/{id}")
    public PsychologueListeDto GetPsychologueById(@PathVariable  Integer id){
        Psychologue psychologue=psyService.GetPsychologueById(id);

        return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());
    }
    @GetMapping("/psychologues/{id}/conseils")
    public List<Conseil> getConseilByPsy(@PathVariable Integer id){
        return psyService.getConseilByPsy(id);
        //Psychologue psychologue=psyService.GetPsychologueById(id);

        //return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());
        //return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());

    }

    @PostMapping(path = "post")
    public Conseil create(@RequestBody ConseilDto conseilDto){
        //System.out.println("***************" + utl);
        //return conseilService.creer(utl);
        Conseil conseil=new Conseil();
        conseil.setTitre(conseilDto.getTitre());
        conseil.setDescription(conseilDto.getDescription());
        conseil.setAuteur(conseilDto.getAuteur());
    }

}
