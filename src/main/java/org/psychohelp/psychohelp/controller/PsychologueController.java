package org.psychohelp.psychohelp.controller;

import jakarta.validation.Valid;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.ConseilService;
import org.psychohelp.psychohelp.service.PsyService;
import org.psychohelp.psychohelp.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/psychologues")
@Tag(
        name = "Psychologues",
        description = "Gestion des Psychologues"
)

public class PsychologueController {
    @Autowired private PsyService psyService;
    @Autowired private SpecialiteService specialiteService;
   @Autowired private ConseilService conseilService;
    @Operation(
            summary = "Créer une psychologue",
            description = "Ajoute un nouveau psychologue"
    )
    @PostMapping
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
    @Operation(
            summary = "Lister toutes les psychologues",
            description = "Retourne la liste  des psychologues"
    )
    @GetMapping

    public List<PsychologueListeDto> psychologueList(){
        return  psyService.PSYCHOLOGUEList();
    }
    @Operation(
            summary ="Modifier un psychologue ",
            description = "Modifier un psychologue"
    )

    @PutMapping("/{id}")
    public PsychologueListeDto updatePsychologue( @RequestBody UpdatePsyDto updatePsyDto ,@PathVariable("id") int PsychologueId){
        return psyService.updatePsychologue(updatePsyDto,PsychologueId);
    }
    @Operation(
            summary ="Modifier l'état d'un psychologue ",
            description = "Modifier l'état d'un psychologue"
    )
    @PatchMapping("/{id}/etatStatus")
    public PsychologueListeDto updateEtatStatus(
            @PathVariable int id,
            @RequestBody UpdateEtatStatusDto psychologue){

        return psyService.UpdateEtatStatus(id, psychologue);
    }
    @Operation(
            summary = "Rechercher un psychologue",
            description = "Retourne un psychologue à partir de son identifiant"
    )

    @GetMapping("/{id}")
    public PsychologueListeDto GetPsychologueById(@PathVariable  Integer id){
        Psychologue psychologue=psyService.GetPsychologueById(id);

        return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());
    }
    @Operation(
            summary = "Rechercher un conseil d'un  psychologue",
            description = "Retourne un conseil d'un psyvhologue à partir de son identifiant"
    )

    @GetMapping("/{id}/conseils")
    public List<Conseil> getConseilByPsy(@PathVariable Integer id){
        return psyService.getConseilByPsy(id);
        //Psychologue psychologue=psyService.GetPsychologueById(id);

        //return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());
        //return new PsychologueListeDto(psychologue.getId(),psychologue.getNom(),psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat());

    }
    @Operation(
            summary = "Crée un conseil ",
            description = "Ajoute un nouveau conseil"
    )
    @PostMapping(path = "/conseil")
    public ListConseilDto create(@RequestBody ConseilDto conseilDto){
        Conseil conseil=new Conseil();
        conseil.setTitre(conseilDto.getTitre());
        conseil.setDescription(conseilDto.getDescription());
        conseil.setAuteur(conseilDto.getAuteur());
        //recuperation de l'id psy à partir de conseil
        Psychologue psychologue=psyService.GetPsychologueById(conseilDto.getPsyId());
        conseil.setPsychologue(psychologue);
        Conseil conseilcreer=conseilService.creer(conseil);
        return new ListConseilDto(conseilcreer.getTitre(),conseilcreer.getDescription(),conseilcreer.getStatus(),conseilcreer.getAuteur());
    }
    @Operation(
            summary = "Liste des Psy validés ",
            description = "Liste des Psy validés par admin"
    )

    @GetMapping("/valide")
    public List<PsyReponseDto> getPsychologueValide() {



        return psyService.getPsychologueValide();
    }

}
