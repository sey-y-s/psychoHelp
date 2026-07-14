package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.psychohelp.psychohelp.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    List<Seance> findByCitoyenId(Long citoyenId);
    List<Seance> findByStatut(StatutRdvEnum statut);

    @Query("SELECT s FROM Seance s JOIN s.creneau c WHERE c.psychologue.id = :psyId")
    List<Seance> findByPsyId(@Param("psyId") int psyId);
    @Query("select  count(s.id) from Seance s where s.dateRdv=:dateRdv and s.creneau.id=:id ")
    int rdvDejaPris(@Param("dateRdv") LocalDate dateRv,@Param("id") Long id);
}