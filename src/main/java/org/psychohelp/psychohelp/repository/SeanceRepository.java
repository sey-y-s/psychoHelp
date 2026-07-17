package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.psychohelp.psychohelp.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    List<Seance> findByCitoyenId(Long citoyenId);
    List<Seance> findByStatut(StatutRdvEnum statut);

    @Query("""
        SELECT s
        FROM Seance s
        WHERE s.creneau.psychologue.id = :psyId
        ORDER BY s.dateRdv DESC,
                 s.creneau.heureDebut ASC
    """)
    List<Seance> findByPsyId(
            @Param("psyId") int psyId
    );
}