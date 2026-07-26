package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.dto.CitoyenRendezVousResponseDTO;
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
    int rdvDejaPris(@Param("dateRdv") LocalDate dateRv,@Param("id") int id);

    @Query("""
    SELECT new org.psychohelp.psychohelp.dto.CitoyenRendezVousResponseDTO(
        s.id,
        p.nom,
        p.prenom,
        sp.nom,
        s.dateRdv,
        c.heureDebut,
        c.heureFin,
        s.statut
    )
    FROM Seance s
    JOIN s.creneau c
    JOIN c.psychologue p
    JOIN p.specialite sp
    WHERE s.citoyen.id = :citoyenId
    ORDER BY s.dateRdv ASC
""")
    List<CitoyenRendezVousResponseDTO> findSeancesByCitoyenConnecte(
            @Param("citoyenId") Long citoyenId
    );

    boolean existsByCreneauIdAndStatutIn(Integer creneauId, List<StatutRdvEnum> statuts);
}