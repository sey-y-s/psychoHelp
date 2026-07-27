package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CreneauRepository extends JpaRepository<Creneau, Integer> {

    List<Creneau> findByStatutTrue();

    List<Creneau> findByPsychologueIdAndStatutTrue(int psychologue_id);

    List<Creneau> findByPsychologueId(int psychologueId);

    @Query("""
        SELECT COUNT(c) > 0
        FROM Creneau c
        WHERE c.psychologue.id = :psychologueId
          AND c.jours = :jours
          AND c.heureDebut < :heureFin
          AND c.heureFin > :heureDebut
    """)
    boolean existeCreneau(
            @Param("psychologueId") Integer psychologueId,
            @Param("jours") String jours,
            @Param("heureDebut") LocalTime heureDebut,
            @Param("heureFin") LocalTime heureFin);
}
