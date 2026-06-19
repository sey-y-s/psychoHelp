package org.psychohelp.psychohelp.controller;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.service.SeanceService;
import org.springframework.web.bind.annotation.*;


import org.psychohelp.psychohelp.entity.Seance;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/seances")
@RequiredArgsConstructor
public class SeanceController {

    private final SeanceService seanceService;

    @GetMapping
    public List<Seance> getAll() {
        return seanceService.getAllSeances();
    }

    @GetMapping("/{id}")
    public Seance getSeanceById(Long id) {
        return seanceService.getSeanceById(id);
    }

    @PostMapping
    public Seance create(@RequestBody Seance seance) {
        return seanceService.createSeance(seance);
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<Seance> annuler(@PathVariable Long id) {
        return ResponseEntity.ok(seanceService.cancelSeance(id));
    }


    @GetMapping("/citoyen/{citoyenId}")
    public List<Seance> getByCitoyen(@PathVariable Long citoyenId) {
        return seanceService.getSeancesByCitoyen(citoyenId);
    }

    @GetMapping("/statut")
    public List<Seance> getByStatut(@RequestParam StatutRdvEnum statut) {
        return seanceService.getSeancesByStatut(statut);
    }

    @GetMapping("/psy/{psyId}")
    public List<Seance> getByPsy(@PathVariable Long psyId) {
        return seanceService.getSeancesByPsy(psyId);
    }
}
