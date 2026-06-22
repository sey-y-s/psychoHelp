package org.psychohelp.psychohelp.controller;

import jakarta.validation.Valid;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.service.PsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PsychologueController {
    @Autowired private PsyService psyService;
    @PostMapping("/psychologue")
    public Psychologue savePsychologue(@Valid @RequestBody Psychologue psychologue){
        return psyService.savePsychologue(psychologue);

    }
    @GetMapping("/psychologue")
    public List<Psychologue> psychologueList(){
        return psyService.PSYCHOLOGUEList();
    }
    @PutMapping("/psychologue/{id}")
    public Psychologue updatePsychologue( @RequestBody Psychologue psychologue ,@PathVariable("id") int PsychologueId){
        return psyService.updatePsychologue(psychologue,PsychologueId);
    }
    @PatchMapping("/psychologue/{id}/etat")
    public Psychologue updateEtat(
            @PathVariable int id,
            @RequestBody Psychologue psychologue){

        return psyService.UpdateEtat(id, psychologue);
    }
}
