package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.dto.CitoyenListeDto;
import org.psychohelp.psychohelp.dto.CitoyenRequestDto;
import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.CitoyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/citoyens")
@RestController
public class CitoyenController {
    @Autowired
    private CitoyenService citoyenService;
    @PostMapping
    public ResponseEntity<String> ajouterCitoyen(@RequestBody CitoyenRequestDto citoyenRequestDto) {
        Citoyen citoyen = new Citoyen();
        citoyen.setNom(citoyenRequestDto.getNom());
        citoyen.setPrenom(citoyenRequestDto.getPrenom());
        citoyen.setTelephone(citoyenRequestDto.getTelephone());
        citoyen.setMail(citoyenRequestDto.getMail());
        citoyen.setMotDePasse(citoyenRequestDto.getMotDePasse());
        citoyen.setRole(RoleEnum.CITOYEN);
        citoyenService.ajouterCitoyen(citoyen);
        return ResponseEntity.status(HttpStatus.CREATED).body("citoyen ajouteé avec succes");

    }
    @GetMapping
    public List<CitoyenListeDto> listerCitoyen() {
        return citoyenService.getAllCitoyen().stream().map(
                citoyen -> new CitoyenListeDto(citoyen.getId(),citoyen.getNom(),citoyen.getPrenom(),citoyen.getTelephone(),citoyen.getMail(),citoyen.getRole().toString())
        ).toList();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CitoyenListeDto>modifierCitoyen(@PathVariable int id,   @RequestBody CitoyenRequestDto citoyenRequestDto) {
        Citoyen citoyenRecuperer = citoyenService.getCitoyenById(id);
        if(citoyenRecuperer==null){
            return ResponseEntity.notFound().build();
        }
        citoyenRecuperer.setNom(citoyenRequestDto.getNom());
        citoyenRecuperer.setPrenom(citoyenRequestDto.getPrenom());
        citoyenRecuperer.setTelephone(citoyenRequestDto.getTelephone());
        citoyenRecuperer.setMail(citoyenRequestDto.getMail());
        citoyenRecuperer.setMotDePasse(citoyenRequestDto.getMotDePasse());
        Citoyen citoyenModifier=      citoyenService.ajouterCitoyen(citoyenRecuperer);
        return ResponseEntity.ok(new CitoyenListeDto(citoyenModifier.getId(),citoyenModifier.getNom(),citoyenModifier.getPrenom(),citoyenModifier.getTelephone(),citoyenModifier.getMail(),citoyenModifier.getRole().toString()) );

    }
    @GetMapping("/{id}")
    public ResponseEntity<CitoyenListeDto> getCitoyenById(@PathVariable int id) {
        Citoyen citoyen = citoyenService.getCitoyenById(id);
        if(citoyen==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( new CitoyenListeDto(citoyen.getId(),citoyen.getNom(),citoyen.getPrenom(),citoyen.getTelephone(),citoyen.getMail(),citoyen.getRole().toString()) );

    }
    @GetMapping("/{id}/rdvs")
    public  List<CitoyenSeanceWithPsychologueDto>  listeSeanceWithIsPsychologue(@PathVariable int id){
        return citoyenService.listeSeanceWithIsPsychologue(id);
    }


}
