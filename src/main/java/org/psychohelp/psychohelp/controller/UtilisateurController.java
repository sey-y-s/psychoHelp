package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.serviceImpl.UtilisateurServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    UtilisateurServiceImpl utilisateurService;

    public UtilisateurController(UtilisateurServiceImpl utlService){
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



}
