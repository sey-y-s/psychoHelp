package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.dto.UpdateSpecialiteDto;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/specialites")

@RestController
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;
    @PostMapping
    public ResponseEntity<String> ajouter(@RequestBody Specialite specialite){
        specialiteService.ajouter(specialite);
        return  ResponseEntity.status(HttpStatus.CREATED).body("specialite iseree avec succes");
    }
    @GetMapping
    public List<SpecialiteListeDto> Liste(){
        return specialiteService.ListeSpecialite().stream().map(
                specialite -> new SpecialiteListeDto(specialite.getId(),specialite.getNom())
        ).toList();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<SpecialiteListeDto> modifier(@PathVariable int id, @RequestBody UpdateSpecialiteDto updateSpecialiteDto){
        Specialite specialite=specialiteService.getSpecialite(id);
        if(specialite==null){
            return ResponseEntity.notFound().build();
        }
        specialite.setNom(updateSpecialiteDto.getNom());
        Specialite specialitemodifiee=   specialiteService.ajouter(specialite);
        return ResponseEntity.ok(new SpecialiteListeDto(specialitemodifiee.getId(),specialitemodifiee.getNom()));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerSepecialite(@PathVariable int id){
        Specialite specialite=specialiteService.getSpecialite(id);
        if(specialite==null){
            return ResponseEntity.notFound().build();
        }
        specialiteService.supprimer(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("suppression effectuee avec succes");
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecialiteListeDto> getSpecialite(@PathVariable  int id){
        Specialite specialite=specialiteService.getSpecialite(id);
        if(specialite==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new SpecialiteListeDto(specialite.getId(),specialite.getNom()));
    }
    @GetMapping("/{id}/psychologues")
    public List<PsychologueListeDto> getSpecialisteIsPysychologue(@PathVariable int id){
        List<Psychologue>psychologues=specialiteService.getSpecialiteIsPsycholoque(id);

        return psychologues.stream().map(
                psychologue ->
                        new PsychologueListeDto(psychologue.getId(),
                        psychologue.getNom(), psychologue.getPrenom(),psychologue.getTelephone(),psychologue.getMail(),
                                psychologue.getRole(),psychologue.getDateCreation(),psychologue.getStatus(),
                                psychologue.getDescription(),psychologue.getDiplome_path(),psychologue.getCv_path(),psychologue.getEtat()
                                )
        ).toList();
    }
}
