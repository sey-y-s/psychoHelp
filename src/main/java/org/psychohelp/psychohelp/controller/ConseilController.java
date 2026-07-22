package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.ConseilAfficheDto;
import org.psychohelp.psychohelp.dto.ConseilDto;
import org.psychohelp.psychohelp.dto.ConseilDtoForPyschologue;
import org.psychohelp.psychohelp.dto.ConseilRequestDTO;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;
import org.psychohelp.psychohelp.exceptions.AccesRefuseException;
import org.psychohelp.psychohelp.exceptions.UnauthorizedException;
import org.psychohelp.psychohelp.service.PsyService;
import org.psychohelp.psychohelp.serviceImpl.ConseilServiceImpl;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/conseils")
@Tag(
        name = "Conseils",
        description = "Gestion des conseils"
)
public class ConseilController {
    @Autowired
    private PsyService psyService;
    @Autowired
    ConseilServiceImpl conseilService;

    public ConseilController(ConseilServiceImpl utlService){
        this.conseilService = utlService;
    }

    @Operation(
            summary = "Liste",
            description = "Voir la liste des conseils"
    )
    @GetMapping(path = "read")
    public List<ConseilAfficheDto> list(@RequestParam (required = false) StatusConseilEnum status){
        if (status != null){
            //return conseilService.listConseilParStatus(status);
            return conseilService.listConseilParStatus(status);
//                    .stream()
//                    .map(
//                            conseil -> new ConseilAfficheDto(conseil.getTitre(),
//
//                                    conseil.getDescription(),conseil.getAuteur(),
//                                    conseil.getPsychologue().nomComplet(),
//                                    conseil.getDatePublication(),
//                                    conseil.getStatus(),
//                                    conseil.getId())
//                    ).toList();
        }
        //return conseilService.listeConseil();
        return conseilService.listeConseil();
//                .stream()
//                .map(
//                        conseil -> new ConseilAfficheDto(conseil.getTitre(),
//                                conseil.getDescription(),conseil.getAuteur(),
//                                conseil.getPsychologue().nomComplet(),
//                                conseil.getDatePublication(),
//                                conseil.getStatus().toString(), conseil.getId())
//                ).toList();

    }


    @Operation(
            summary = "Récuperer un conseil",
            description = "recuperer un seul conseils par son identifiant"
    )
    @GetMapping(path = "{id}")
    public ConseilDtoForPyschologue conseilById(@PathVariable int id){
        Conseil conseil = conseilService.conseilParId(id);
        ConseilDtoForPyschologue conseilDtoForPyschologue = new ConseilDtoForPyschologue();
        conseilDtoForPyschologue.setTitre(conseil.getTitre());
        conseilDtoForPyschologue.setDescription(conseil.getDescription());
        conseilDtoForPyschologue.setDatePublication(conseil.getDatePublication());
        conseilDtoForPyschologue.setId(conseil.getId());
        conseilDtoForPyschologue.setStatusConseil(conseil.getStatus());

        return conseilDtoForPyschologue;
    }

    @Operation(
            summary = "Créer un conseils",
            description = "Inserer un conseils"
    )
    @PostMapping(path = "post")
    public ResponseEntity<ConseilDtoForPyschologue> create(@RequestBody ConseilRequestDTO conseilDto, HttpSession session){

        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
        Psychologue psy =  psyService.GetPsychologueById(utilisateur.getId());

        //System.out.println("***************" + utl);
        Conseil conseil =  new Conseil();
        conseil.setTitre(conseilDto.getTitre());
        conseil.setDescription(conseilDto.getDescription());
        conseil.setAuteur("");
        conseil.setStatus(StatusConseilEnum.ENATTENTE);
        conseil.setDatePublication(LocalDate.now());
        /*conseil.setPsychologue(
                conseilService.conseilParId(conseilDto.getPsy_id()).getPsychologue()
        );*/

        //Psychologue psy = psyService.GetPsychologueById(conseilDto.getPsyId());
        conseil.setPsychologue(psy);
        Conseil conseilrer=conseilService.creer(conseil);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ConseilDtoForPyschologue(conseilrer.getId(), conseilrer.getTitre(),conseilrer.getDescription(),conseilrer.getStatus(),conseilrer.getDatePublication())
        );
    }


    @Operation(
            summary = "Modification d'un conseil",
            description = "modifier un conseil par son id"
    )
    @PutMapping(path = "update/{id}")
    public ConseilDtoForPyschologue update(@PathVariable int id, @RequestBody ConseilRequestDTO conseilDto, HttpSession session){
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");

        Conseil conseil = conseilService.conseilParId(id);
        if (conseil.getPsychologue().getId() == utilisateur.getId()){

            conseil.setTitre(conseilDto.getTitre());
            conseil.setDescription(conseilDto.getDescription());

            Conseil conseilModif= conseilService.modifier(id, conseil);

            return new ConseilDtoForPyschologue(conseilModif.getId(),conseilModif.getTitre(),conseilModif.getDescription(),
                    conseilModif.getStatus(),conseilModif.getDatePublication());
        }
           throw new UnauthorizedException("ce conseil ne vous appartient pas!");
    }


    @Operation(
            summary = "Suppression",
            description = "Supprimer un conseil"
    )
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id, HttpSession session){
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");
        Conseil conseil = conseilService.conseilParId(id);
        if (conseil.getPsychologue().getId() == utilisateur.getId()){
            conseilService.supConseil(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new UnauthorizedException("Vous n'avez pas les droits nessessaires pour supprimer cette ressource!");
    }

    @GetMapping("mes-conseils")
    public  List<ConseilDtoForPyschologue> ConseilsByPyschologueId(HttpSession session){
        Session.verifierRole(session, RoleEnum.PSYCHOLOGUE);
        return conseilService.ConseilsByPyschologueId(session);
    }
}