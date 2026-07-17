package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.ConseilAfficheDto;
import org.psychohelp.psychohelp.dto.ConseilDto;
import org.psychohelp.psychohelp.dto.ConseilRequestDTO;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;
import org.psychohelp.psychohelp.service.PsyService;
import org.psychohelp.psychohelp.serviceImpl.ConseilServiceImpl;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/conseils")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(
        name = "Conseils",
        description = "Gestion des conseils"
)
public class ConseilController {

    @Autowired
    private PsyService psyService;

    ConseilServiceImpl conseilService;

    public ConseilController(ConseilServiceImpl utlService){
        this.conseilService = utlService;
    }

    @Operation(
            summary = "Liste",
            description = "Voir la liste des conseils"
    )
    @GetMapping(path = "read")
    public List<ConseilAfficheDto> list(@RequestParam(required = false) Boolean status) {

        if (status != null) {
            return conseilService.listConseilParStatus(status);
        }
        return conseilService.listeConseil();
    }


    @Operation(
            summary = "Récuperer un conseil",
            description = "recuperer un seul conseils par son identifiant"
    )
    @GetMapping(path = "{id}")
    public ConseilAfficheDto conseilById(@PathVariable int id){
        Conseil conseil = conseilService.conseilParId(id);
        ConseilAfficheDto conseilDto = new ConseilAfficheDto();
        conseilDto.setTitre(conseil.getTitre());
        conseilDto.setDescription(conseil.getDescription());
        conseilDto.setAuteur(conseil.getAuteur());
        conseilDto.setPsyNom(conseil.getPsychologue().getNom());
        conseilDto.setDatePublication(conseil.getDatePublication());
        conseilDto.setStatus(conseil.getStatus().name());
        conseilDto.setId(conseil.getId());
        //conseilDto.setPsyId(conseil.getPsychologue().getId());
        return conseilDto;
    }

    @Operation(
            summary = "Créer un conseils",
            description = "Inserer un conseils"
    )
    @PostMapping(path = "post")
    public ConseilRequestDTO create(@RequestBody ConseilRequestDTO conseilDto, HttpSession session){

        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
        Psychologue psy =  psyService.GetPsychologueById(utilisateur.getId());

        //System.out.println("***************" + utl);
        Conseil conseil =  new Conseil();
        conseil.setTitre(conseilDto.getTitre());
        conseil.setDescription(conseilDto.getDescription());
        conseil.setAuteur(conseilDto.getDescription());
        conseil.setStatus(StatusConseilEnum.ENATTENTE);
        conseil.setDatePublication(LocalDate.now());
        /*conseil.setPsychologue(
                conseilService.conseilParId(conseilDto.getPsy_id()).getPsychologue()
        );*/

        //Psychologue psy = psyService.GetPsychologueById(conseilDto.getPsyId());
        conseil.setPsychologue(psy);
         conseilService.creer(conseil);
        return conseilDto;
    }


    @Operation(
            summary = "Modification d'un conseil",
            description = "modifier un conseil par son id"
    )
    @PutMapping(path = "update/{id}")
    public ConseilRequestDTO update(@PathVariable int id, @RequestBody ConseilRequestDTO conseilDto, HttpSession session){
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");

        Conseil conseil = conseilService.conseilParId(id);
        if (conseil.getPsychologue().getId() == utilisateur.getId()){

        conseil.setTitre(conseilDto.getTitre());
        conseil.setAuteur(conseilDto.getAuteur());
        conseil.setDescription(conseilDto.getDescription());
        conseilService.modifier(id, conseil);

        return conseilDto;
        }
        return null;
    }


    @Operation(
            summary = "Suppression",
            description = "Supprimer un conseil"
    )
    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable int id, HttpSession session){
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
        Conseil conseil = conseilService.conseilParId(id);
        if (conseil.getPsychologue().getId() == utilisateur.getId()){
            conseilService.supConseil(id);
            return "Conseils supprimer";
        }
        return "Vous n'avez pas les droits nessessaires pour supprimer cette ressource";
    }

}
