package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.CitoyenListeDto;
import org.psychohelp.psychohelp.dto.CitoyenRequestDto;
import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.service.CitoyenService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Tag(
        name = "citoyen ",
        description = "Gestion des citoyens"
)
@RequestMapping("/api/citoyens")
@RestController
public class CitoyenController {
    @Autowired
    private CitoyenService citoyenService;
    @Operation(
            summary = "Créer une nouveau citoyen",
            description = "Ajoute une nouveau citoyen "
    )
    @PostMapping
    public ResponseEntity<String> ajouterCitoyen(@RequestBody CitoyenRequestDto citoyenRequestDto) {
        citoyenService.ajouterCitoyen(citoyenRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("citoyen ajouteé avec succes");

    }
    @Operation(
            summary = "liste des citoyens",
            description = "affiche la  liste des citoyens "
    )
    @GetMapping
    public List<CitoyenListeDto> listerCitoyen(HttpSession session) {
        Session.verifierRole(session,RoleEnum.ADMIN);

        return citoyenService.getAllCitoyen().stream().map(
                citoyen -> new CitoyenListeDto(citoyen.getId(),citoyen.getNom(),citoyen.getPrenom(),citoyen.getTelephone(),citoyen.getMail(),citoyen.getRole().toString())
        ).toList();
    }
    @Operation(
            summary = "modifie un citoyen ",
            description = "modifier un citoyen "
    )
    @PatchMapping
    public CitoyenListeDto modifierCitoyen(@RequestBody CitoyenRequestDto citoyenRequestDto,HttpSession session) {
        Session.verifierRole(session,RoleEnum.CITOYEN);
        return citoyenService.modifierCitoyen(citoyenRequestDto,session);

    }
    @Operation(
            summary = "citoyen specifique",
            description = "affiche un citoyen specifique "
    )
    @GetMapping("/{id}")
    public CitoyenListeDto getCitoyenById(@PathVariable int id,HttpSession session) {
        Session.verifierRole(session,RoleEnum.ADMIN);
        return  citoyenService.getCitoyenById(id);

    }
    @Operation(
            summary = "affiche les rdv pris par un citoyen",
            description = "affiche les rdv pris par un citoyen "
    )
    @GetMapping("/rdvs")
    public  List<CitoyenSeanceWithPsychologueDto>  listeSeanceWithIsPsychologue(HttpSession session) {

        Session.verifierRole(session,RoleEnum.CITOYEN);
        Utilisateur citoyensession=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        System.out.println(citoyensession.getId());
        return citoyenService.listeSeanceWithIsPsychologue(citoyensession.getId());
    }
    @Operation(
            summary = "donne la possibilité à l'admin d'afficher les rdv pris par un citoyen ",
            description = "affiche les rdv pris par un citoyen "
    )
    @GetMapping("/{id}/rdvs")
    public  List<CitoyenSeanceWithPsychologueDto>  listeSeanceWithIsPsychologue(@PathVariable int id, HttpSession session) {

        Session.verifierRole(session,RoleEnum.ADMIN);
        return citoyenService.listeSeanceWithIsPsychologue(id);
    }
    


}
