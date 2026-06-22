package org.psychohelp.psychohelp.controller;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.service.CreneauService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creneaux")
public class CreneauController {


    private final CreneauService cs;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Creneau creer(@RequestBody Creneau creneau) {
        return cs.creer(creneau);
    }

    @GetMapping
    public List<Creneau> getAll() {
        return cs.getAll();
    }

    @GetMapping("/{id}")
    public Creneau getById(@PathVariable Long id) {
        return cs.getById(id);
    }

    @PutMapping("/{id}")
    public Creneau update(Long id, @RequestBody Creneau creneau) {
        return cs.update(id, creneau);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        cs.delete(id);
    }

    @PostMapping("/{id}/reserver")
    public Creneau reserver(@PathVariable Long id) {
        return cs.reserver(id);
    }

    @PutMapping("/{id}/annuler")
    public Creneau annuler(@PathVariable Long id) {
        return cs.annuler(id);
    }

    @PutMapping("/{id}/confimer")
    public Creneau confirmer(@PathVariable Long id) {
        return cs.confirmer(id);
    }

    @GetMapping("/disponibles")
    public List<Creneau> getDisponibles() {
        return cs.getDisponibles();
    }

    @GetMapping("/disponibles/{psychologueId}")
    public List<Creneau> getDisponiblesByPsychologueId(@PathVariable Integer psychologueId) {
        return cs.getDisponiblesByPsychologueId(psychologueId);
    }
}
