package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.dto.RequestSpecialiteDto;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.SpecialiteService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequestMapping("/api/specialites")

@RestController
@Tag(
        name = "Specialité des psychologues",
        description = "Gestion de la specialité des psychologues"
)
public class SpecialiteController{
    @Autowired
    private SpecialiteService specialiteService;
    @Operation(
            summary = "Créer une nouvelle specialité",
            description = "Ajoute une nouvelle specialité "
    )
    @PostMapping
    public ResponseEntity<String> ajouter(@RequestBody RequestSpecialiteDto updateSpecialiteDto, HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        specialiteService.ajouter(updateSpecialiteDto,session);
        return  ResponseEntity.status(HttpStatus.CREATED).body("specialite iseree avec succes");
    }
    @Operation(
            summary = "La liste des specialités",
            description = "ici on affiche la liste des specialités"
    )
    @GetMapping
    public List<SpecialiteListeDto> Liste(HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN, RoleEnum.PSYCHOLOGUE);
        return specialiteService.listeSpecialite(session).stream().map(
                specialite -> new SpecialiteListeDto(specialite.getId(),specialite.getNom(),specialite.getAdmin().getNom())
        ).toList();
    }
    @Operation(
            summary = "modification d'une specialité specifique",
            description = "ici on modifie une specialité specifique"
    )
    @PatchMapping("/{id}")
    public SpecialiteListeDto modifier(@PathVariable int id, @RequestBody RequestSpecialiteDto updateSpecialiteDto, HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        SpecialiteListeDto specialiteListeDto   =specialiteService.updateSpecialite(id,updateSpecialiteDto,session);
        return specialiteListeDto;

    }
    @Operation(
            summary = "suppression d'un  specialité",
            description = "ici on supprime une specialité specifique"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerSepecialite(@PathVariable int id,HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        specialiteService.supprimer(id,session);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("suppression effectuee avec succes");
    }
    @Operation(
            summary = "specialité specifique",
            description = "ici on affiche une specialité specifique"
    )
    @GetMapping("/{id}")
    public SpecialiteListeDto getSpecialite(@PathVariable  int id, HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        return specialiteService.getSpecialite(id, session);
    }
    @Operation(
            summary = "les pychologues qui ont cette specialité",
            description = "ici on affiche les psychologues qui ont cette specialité"
    )
    @GetMapping("/{id}/psychologues")
    public List<PsychologueListeDto> getSpecialisteIsPysychologue(@PathVariable int id,HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        List<Psychologue>psychologues=specialiteService.getSpecialiteIsPsycholoque(id);

        return psychologues.stream().map(
                PsychologueController::mapPsytoDto
        ).toList();
    }

    @GetMapping("/public")
    public List<SpecialiteListeDto> listePublique() {

        return specialiteService.listePublique()
                .stream()
                .map(s -> new SpecialiteListeDto(
                        s.getId(),
                        s.getNom(),
                        ""
                ))
                .toList();
    }
}
