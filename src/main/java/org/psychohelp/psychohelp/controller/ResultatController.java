package org.psychohelp.psychohelp.controller;

import org.psychohelp.psychohelp.entity.ResultatTest;
import org.psychohelp.psychohelp.service.ResultatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {


    private final ResultatService resultatService;


    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }


    @PostMapping("/calculer")
    public ResponseEntity<ResultatTest> calculerEtEnregistrerResultat(
            @RequestParam Integer citoyenId,
            @RequestParam Integer testId,
            @RequestBody List<Integer> choixIds) {

        ResultatTest resultat = resultatService.calculerEtEnregistrerResultat(citoyenId, testId, choixIds);
        return new ResponseEntity<>(resultat, HttpStatus.CREATED);
    }

    @GetMapping("/citoyen/{citoyenId}")
    public ResponseEntity<List<ResultatTest>> obtenirResultatsParCitoyen(@PathVariable Integer citoyenId) {
        List<ResultatTest> historique = resultatService.obtenirResultatsParCitoyen(citoyenId);
        return ResponseEntity.ok(historique);
    }
}
