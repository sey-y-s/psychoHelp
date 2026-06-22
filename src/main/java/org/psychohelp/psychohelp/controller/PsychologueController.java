package org.psychohelp.psychohelp.controller;

import jakarta.validation.Valid;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.PsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PsychologueController {
    @Autowired private PsyService psyService;
    @PostMapping("/psychologues")
    public Psychologue savePsychologue(@Valid @RequestBody Psychologue psychologue){
       // psychologue.setDateCreation(LocalDate.now());
        return psyService.savePsychologue(psychologue);


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

}
