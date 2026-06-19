package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Seance;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;

import java.util.List;

public interface SeanceService {
    List<Seance> getAllSeances();
    Seance getSeanceById(Long id);
    Seance createSeance(Seance seance);
    Seance cancelSeance(Long id);

    List<Seance> getSeancesByCitoyen(Long citoyenId);
    List<Seance> getSeancesByStatut(StatutRdvEnum statut);
    List<Seance> getSeancesByPsy(Long psyId);
}
