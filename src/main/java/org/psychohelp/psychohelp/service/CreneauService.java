package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Creneau;

import java.util.List;

public interface CreneauService {

    Creneau creer(Creneau creneau);

    List<Creneau> getAll();

    Creneau getById(Long id);

    Creneau update(Long id, Creneau creneau);

    void delete(Long id);

    Creneau reserver(Long id);

    Creneau annuler(Long id);

    Creneau confirmer(Long id);

    List<Creneau> getDisponibles();

    List<Creneau> getDisponiblesByPsychologueId(Integer psychologueId);
}
