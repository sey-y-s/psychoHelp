package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.serviceImpl.ConseilServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conseils")
public class ConseilController {

    ConseilServiceImpl conseilService;

    public ConseilController(ConseilServiceImpl utlService){
        this.conseilService = utlService;
    }


    @GetMapping(path = "list")
    public List<Conseil> list(){
        return conseilService.listeConseil();
    }


    @GetMapping(path = "{id}")
    public Conseil userById(@PathVariable int id){
        return conseilService.conseilParId(id);
    }

    @PostMapping
    public Conseil create(@RequestBody Conseil utl){
        return conseilService.creer(utl);
    }

    @GetMapping(path = "/test")
    public String test() {
        return "test";
    }


    @PutMapping(path = "update/{id}")
    public Conseil update(@PathVariable int id, @RequestBody Conseil utl){
        return conseilService.modifier(id, utl);
    }


    @DeleteMapping(path = "delete/{id}")
    public void delete(@PathVariable int id){
        conseilService.supConseil(id);
    }



}
