package org.psychohelp.psychohelp.controller;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.psychohelp.psychohelp.service.ChoixMultipleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/choix")
@AllArgsConstructor
public class ChoixMultipleController {
    private final ChoixMultipleService choixService;




    @GetMapping
    public List<ChoixMultiple> getAllChoix(){

        return choixService.getAllChoix();

    }



    @GetMapping("/{id}")
    public ChoixMultiple getChoixById(
            @PathVariable int id){

        return choixService.getChoixById(id);

    }



    @PostMapping
    public ChoixMultiple saveChoix(
            @RequestBody ChoixMultiple choix){

        return choixService.saveChoix(choix);

    }



    @PutMapping("/{id}")
    public ChoixMultiple updateChoix(
            @PathVariable int id,
            @RequestBody ChoixMultiple choix){


        return choixService.updateChoix(id, choix);

    }



    @DeleteMapping("/{id}")
    public void deleteChoix(
            @PathVariable int id){

        choixService.deleteChoix(id);

    }
}
