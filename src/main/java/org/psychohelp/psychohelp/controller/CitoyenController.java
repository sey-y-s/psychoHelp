package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.CitoyenListeDto;
import org.psychohelp.psychohelp.dto.CitoyenRequestDto;
import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Citoyen;
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
    @PatchMapping("/{id}")
    public ResponseEntity<CitoyenListeDto>modifierCitoyen(@PathVariable int id,   @RequestBody CitoyenRequestDto citoyenRequestDto,HttpSession session) {
        Session.verifierRole(session,RoleEnum.CITOYEN);

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
    @Operation(
            summary = "citoyen specifique",
            description = "affiche un citoyen specifique "
    )
    @GetMapping("/{id}")
    public ResponseEntity<CitoyenListeDto> getCitoyenById(@PathVariable int id,HttpSession session) {
        Session.verifierRole(session,RoleEnum.ADMIN);

        Citoyen citoyen = citoyenService.getCitoyenById(id);
        if(citoyen==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( new CitoyenListeDto(citoyen.getId(),citoyen.getNom(),citoyen.getPrenom(),citoyen.getTelephone(),citoyen.getMail(),citoyen.getRole().toString()) );

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
