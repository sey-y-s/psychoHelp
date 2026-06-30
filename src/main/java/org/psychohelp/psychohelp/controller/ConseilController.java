package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.psychohelp.psychohelp.dto.ConseilDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.service.PsyService;
import org.psychohelp.psychohelp.serviceImpl.ConseilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    ConseilServiceImpl conseilService;

    public ConseilController(ConseilServiceImpl utlService){
        this.conseilService = utlService;
    }

    @Operation(
            summary = "Liste",
            description = "voir la liste des conseils"
    )
    @GetMapping(path = "read")
    public List<ConseilDto> list(@RequestParam (required = false) Boolean status){
        if (status != null){
            //return conseilService.listConseilParStatus(status);
            return conseilService.listConseilParStatus(status).stream()
                    .map(
                            conseil -> new ConseilDto(conseil.getTitre(), conseil.getDescription(),conseil.getAuteur(), conseil.getPsychologue().getId())
                    ).toList();
        }
        //return conseilService.listeConseil();
        return conseilService.listeConseil().stream()
                .map(
                        conseil -> new ConseilDto(conseil.getTitre(), conseil.getDescription(),conseil.getAuteur(), conseil.getPsychologue().getId())
                ).toList();
    }


    @Operation(
            summary = "Récuperer un conseil",
            description = "recuperer un seul conseils par son identifiant"
    )
    @GetMapping(path = "{id}")
    public ConseilDto conseilById(@PathVariable int id){
        Conseil conseil = conseilService.conseilParId(id);
        ConseilDto conseilDto = new ConseilDto();
        conseilDto.setTitre(conseil.getTitre());
        conseilDto.setDescription(conseil.getDescription());
        conseilDto.setAuteur(conseil.getAuteur());
        conseilDto.setPsyId(conseil.getPsychologue().getId());
        return conseilDto;
    }

    @Operation(
            summary = "Créer un conseils",
            description = "Inserer un conseils"
    )
    @PostMapping(path = "post")
    public ConseilDto create(@RequestBody ConseilDto conseilDto){
        //System.out.println("***************" + utl);
        Conseil conseil =  new Conseil();
        conseil.setTitre(conseilDto.getTitre());
        conseil.setDescription(conseilDto.getDescription());
        conseil.setAuteur(conseilDto.getDescription());
        conseil.setStatus(false);
        conseil.setDatePublication(LocalDate.now());
        /*conseil.setPsychologue(
                conseilService.conseilParId(conseilDto.getPsy_id()).getPsychologue()
        );*/

        Psychologue psy = psyService.GetPsychologueById(conseilDto.getPsyId());
        //System.out.println("**********test" + psy);
        conseil.setPsychologue(psy);
         conseilService.creer(conseil);
        return conseilDto;
    }


    @Operation(
            summary = "Modification d'un conseil",
            description = "modifier un conseil par son id"
    )
    @PutMapping(path = "update/{id}")
    public ConseilDto update(@PathVariable int id, @RequestBody ConseilDto conseilDto){

        Conseil conseil = conseilService.conseilParId(id);
        conseil.setTitre(conseilDto.getTitre());
        conseil.setAuteur(conseilDto.getAuteur());
        conseil.setDescription(conseilDto.getDescription());


        conseilService.modifier(id, conseil);
        return conseilDto;
    }


    @Operation(
            summary = "Suppression",
            description = "Supprimer un conseil"
    )
    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable int id){
        conseilService.supConseil(id);
        return "Conseils supprimer";
    }



}
