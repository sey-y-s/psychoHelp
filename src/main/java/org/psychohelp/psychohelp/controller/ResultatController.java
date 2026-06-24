package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.psychohelp.psychohelp.entity.ResultatTest;
import org.psychohelp.psychohelp.service.ResultatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
@Tag(
        name = "Resultat de tests",
        description = "Gestion des resultats de tests psychologiques"
)
public class ResultatController {


    private final ResultatService resultatService;


    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @Operation(
            summary = "Calculer et enregistrer le resultat test",
            description = "Faire le calcul des tests tenant compte des scores et enfin enregistrer"
    )

    @PostMapping("/calculer")
    public ResponseEntity<ResultatTest> calculerEtEnregistrerResultat(
            @RequestParam Integer citoyenId,
            @RequestParam Integer testId,
            @RequestBody List<Integer> choixIds) {

        ResultatTest resultat = resultatService.calculerEtEnregistrerResultat(citoyenId, testId, choixIds);
        return new ResponseEntity<>(resultat, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Obtention des resultats d'un citoyen specifique",
            description = "Obtenir les resultats d'un citoyen specifique "
    )

    @GetMapping("/citoyen/{citoyenId}")
    public ResponseEntity<List<ResultatTest>> obtenirResultatsParCitoyen(@PathVariable Integer citoyenId) {
        List<ResultatTest> historique = resultatService.obtenirResultatsParCitoyen(citoyenId);
        return ResponseEntity.ok(historique);
    }
}
