package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
            summary = "Liste",
            description = "voir la liste des utilisateurs"
    )
    @GetMapping(path = "list")
    public List<Utilisateur> list(){
        return utilisateurService.listeUtilisateur();
    }


    @Operation(
            summary = "Un utilisateur",
            description = "Recuperer un utilisateur par son identifiant"
    )
    @GetMapping(path = "{id}")
    public Utilisateur userById(@PathVariable int id){
        return utilisateurService.utilisateurParId(id);
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
    public Utilisateur update(@PathVariable int id, @RequestBody Utilisateur utl){
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

        return ResponseEntity.ok("vous etes connecté !");


    }






}
