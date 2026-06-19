package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utlService){
        this.utilisateurService = utlService;
    }


    @GetMapping(path = "list")
    public List<Utilisateur> list(){
        return utilisateurService.listeUtilisateur();
    }

    @GetMapping(path = "{id}")
    public Utilisateur userById(@PathVariable int id){
        return utilisateurService.utilisateurParId(id);
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utl){
        return utilisateurService.creer(utl);
    }




}
