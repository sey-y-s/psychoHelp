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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;
@RequestMapping("/api/specialites")

@RestController
@Tag(
        name = "Specialité des psychologues",
        description = "Gestion de la specialité des psychologues"
)
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;
    @Operation(
            summary = "Créer une nouvelle specialité",
            description = "Ajoute une nouvelle specialité "
    )
    @PostMapping
    public ResponseEntity<String> ajouter(@RequestBody UpdateSpecialiteDto updateSpecialiteDto){

        specialiteService.ajouter(updateSpecialiteDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body("specialite iseree avec succes");
    }
    @Operation(
            summary = "La liste des specialités",
            description = "ici on affiche la liste des specialités"
    )
    @GetMapping
    public List<SpecialiteListeDto> Liste(){
        return specialiteService.ListeSpecialite().stream().map(
                specialite -> new SpecialiteListeDto(specialite.getId(),specialite.getNom())
        ).toList();
    }
    @Operation(
            summary = "modification d'une specialité specifique",
            description = "ici on modifie une specialité specifique"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<SpecialiteListeDto> modifier(@PathVariable int id, @RequestBody UpdateSpecialiteDto updateSpecialiteDto){
        SpecialiteListeDto specialiteListeDto   =specialiteService.updateSpecialite(id,updateSpecialiteDto);
        if(specialiteListeDto==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(specialiteListeDto);

    }
    @Operation(
            summary = "suppression d'un  specialité",
            description = "ici on supprime une specialité specifique"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerSepecialite(@PathVariable int id){
        Specialite specialite=specialiteService.getSpecialite(id);
        if(specialite==null){
            return ResponseEntity.notFound().build();
        }
        specialiteService.supprimer(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("suppression effectuee avec succes");
    }
    @Operation(
            summary = "specialité specifique",
            description = "ici on affiche une specialité specifique"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SpecialiteListeDto> getSpecialite(@PathVariable  int id){
        Specialite specialite=specialiteService.getSpecialite(id);
        if(specialite==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new SpecialiteListeDto(specialite.getId(),specialite.getNom()));
    }
    @Operation(
            summary = "les pychologues qui ont cette specialité",
            description = "ici on affiche les psychologues qui ont cette specialité"
    )
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
