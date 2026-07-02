package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.psychohelp.psychohelp.dto.ResultatTestRequestDTO;
import org.psychohelp.psychohelp.dto.ResultatTestResponseDTO;
import org.psychohelp.psychohelp.service.ResultatService;
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

    // Injection par constructeur (recommandée)
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @Operation(
            summary = "Calculer et enregistrer le resultat test",
            description = "Faire le calcul des tests tenant compte des scores, générer le diagnostic et enregistrer le résultat."
    )
    @PostMapping("/calculer")
    public ResponseEntity<ResultatTestResponseDTO> calculerEtEnregistrerResultat(
            @RequestBody ResultatTestRequestDTO requestDTO) {

        ResultatTestResponseDTO resultat = resultatService.calculerEtEnregistrerResultat(requestDTO);
        return new ResponseEntity<>(resultat, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtention des resultats d'un citoyen specifique",
            description = "Récupérer tout l'historique des résultats de tests pour un citoyen donné."
    )
    @GetMapping("/citoyen/{citoyenId}")
    public ResponseEntity<List<ResultatTestResponseDTO>> obtenirResultatsParCitoyen(
            @PathVariable Integer citoyenId) {

        List<ResultatTestResponseDTO> historique = resultatService.obtenirResultatsParCitoyen(citoyenId);
        return ResponseEntity.ok(historique);
    }
}