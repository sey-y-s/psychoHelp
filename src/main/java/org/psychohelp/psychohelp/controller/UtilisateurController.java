package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.Seance;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.repository.ConseilRepository;
import org.psychohelp.psychohelp.repository.TestRepository;
import org.psychohelp.psychohelp.service.AuthentificationService;
import org.psychohelp.psychohelp.service.UtilisateurService;
import org.psychohelp.psychohelp.serviceImpl.UtilisateurServiceImpl;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
@Tag(name = "Utilisateur", description = "Les opération sur l'utilisateur")
@AllArgsConstructor

public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthentificationService authentificationService;
    @Autowired private ConseilRepository conseilRepository;
    @Autowired private TestRepository testRepository;



    @Operation(summary = "Listes", description = "voir la liste des utilisateurs")
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


    @Operation(summary = "Un utilisateur", description = "Recuperer un utilisateur par son identifiant")
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

    @Operation(summary = "Insertion", description = "Inserer un nouvel utilisateur")
    @PostMapping(path = "post")
    public Utilisateur create(@RequestBody Utilisateur utl){
        return utilisateurService.creer(utl);
    }



    @Operation(summary = "Modification", description = "Modifier un utilisateur à partir de l'id")
    @PutMapping(path = "update/{id}")
    public Utilisateur update(@PathVariable int id, @RequestBody Utilisateur utl, HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");

        if (!(utilisateur.getId() == id)){
            return null;
        }
        return utilisateurService.modifier(id, utl);
    }


    @Operation(summary = "Suppression", description = "Supprimer un utilisateur")
    @DeleteMapping(path = "delete/{id}")
    public void delete(@PathVariable int id){
        utilisateurService.supUtilisateur(id);
    }


    @Operation(summary = "Le Dashboard", description = "voir les statistiques")
    @GetMapping(path = "/dashboard")
    public DashboardAdminDTO getDashboard(HttpSession session){
        Session.verifierRole(session, RoleEnum.ADMIN);
        List<UtilisateurListDTO> users = utilisateurService.getRecentUsers().stream().map(
                user -> new UtilisateurListDTO(
                        user.getNom(),
                        user.getPrenom(),
                        user.getMail(),
                        user.getTelephone()

                )
        ).toList();

        DashboardAdminDTO dashboard = new DashboardAdminDTO(
                utilisateurService.count(),
                16,
                conseilRepository.count(),
                testRepository.count(),
                users
        );
        return dashboard;
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



    @GetMapping("/session")
    @Operation( summary = "Récupérer le user courant", description = "Vérifier si une session existe déjà et renvoyer l'utilisateur")
    @ApiResponse(responseCode = "200", description = "Utilisateur connecté récupéré avec succès")
    public AdminResponseDTO getCurrentUser(HttpSession session) {
        return Session.getConnectedUser(session);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().body(Map.of("message", "Déconnecté avec succès"));
    }
}
