package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.ConnectionDTO;
import org.psychohelp.psychohelp.dto.ConnectionReponseDTO;
import org.psychohelp.psychohelp.dto.UtilisateurListDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.service.AuthentificationService;
import org.psychohelp.psychohelp.service.UtilisateurService;
import org.psychohelp.psychohelp.serviceImpl.UtilisateurServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(
        name = "Utilisateur",
        description = "Les opération sur l'utilisateur"
)
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthentificationService authentificationService;

    public UtilisateurController(UtilisateurServiceImpl utilisateurService,
                                 AuthentificationService authentificationService
                                 ){
        this.utilisateurService = utilisateurService;
        this.authentificationService = authentificationService;
    }


    @Operation(
            summary = "Listes",
            description = "voir la liste des utilisateurs"
    )
    @GetMapping(path = "list")
    public List<UtilisateurListDTO> list(){
        return utilisateurService.listeUtilisateur().stream()
                .map(utilisateur -> new UtilisateurListDTO(
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getMail(),
                        utilisateur.getTelephone()
                )
        ).toList();
    }


    @Operation(
            summary = "Un utilisateur",
            description = "Recuperer un utilisateur par son identifiant"
    )
    @GetMapping(path = "{id}")
    public UtilisateurListDTO userById(@PathVariable int id){
        Utilisateur utilisateur = utilisateurService.utilisateurParId(id);
        UtilisateurListDTO utilisateurDTO = new UtilisateurListDTO();
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrenom(utilisateur.getPrenom());
        utilisateurDTO.setMail(utilisateur.getMail());
        utilisateurDTO.setTelephone(utilisateur.getTelephone());

        return utilisateurDTO;
    }

    @Operation(
            summary = "Insertion",
            description = "Inserer un nouvel utilisateur"
    )
    @PostMapping(path = "post")
    public Utilisateur create(@RequestBody Utilisateur utl){
        return utilisateurService.creer(utl);
    }



    @Operation(
            summary = "Modification",
            description = "Modifier un utilisateur à partir de l'id"
    )
    @PutMapping(path = "update/{id}")
    public Utilisateur update(@PathVariable int id, @RequestBody Utilisateur utl, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");

        if (!(utilisateur.getId() == id)){
            return null;
        }
        return utilisateurService.modifier(id, utl);
    }


    @Operation(
            summary = "Suppression",
            description = "Supprimer un utilisateur"
    )
    @DeleteMapping(path = "delete/{id}")
    public void delete(@PathVariable int id){
        utilisateurService.supUtilisateur(id);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ConnectionDTO connectionDTO, HttpSession session){
        Utilisateur utilisateur = authentificationService.connecter(connectionDTO);
        session.setAttribute("UtilisateurConnecte", utilisateur);
        ConnectionReponseDTO dto = new ConnectionReponseDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setMail(utilisateur.getMail());
        dto.setRole(utilisateur.getRole());


        return ResponseEntity.ok(dto);


    }






}
