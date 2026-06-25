package org.psychohelp.psychohelp.controller;

import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.psychohelp.psychohelp.dto.ConnectionDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.service.AuthentificationService;
import org.psychohelp.psychohelp.service.UtilisateurService;
import org.psychohelp.psychohelp.serviceImpl.UtilisateurServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthentificationService authentificationService;

    public UtilisateurController(UtilisateurServiceImpl utilisateurService,
                                 AuthentificationService authentificationService
                                 ){
        this.utilisateurService = utilisateurService;
        this.authentificationService = authentificationService;
    }


    @GetMapping(path = "list")
    public List<Utilisateur> list(){
        return utilisateurService.listeUtilisateur();
    }


    @GetMapping(path = "{id}")
    public Utilisateur userById(@PathVariable int id){
        return utilisateurService.utilisateurParId(id);
    }

    @PostMapping(path = "post")
    public Utilisateur create(@RequestBody Utilisateur utl){
        return utilisateurService.creer(utl);
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test";
    }


    @PutMapping(path = "update/{id}")
    public Utilisateur update(@PathVariable int id, @RequestBody Utilisateur utl){
        return utilisateurService.modifier(id, utl);
    }


    @DeleteMapping(path = "delete/{id}")
    public void delete(@PathVariable int id){
        utilisateurService.supUtilisateur(id);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ConnectionDTO connectionDTO, HttpSession session){
        Utilisateur utilisateur = authentificationService.connecter(connectionDTO);

        session.setAttribute("UtilisateurConnecte", utilisateur);

        return ResponseEntity.ok(utilisateur);


    }






}
