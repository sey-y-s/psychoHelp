package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PsychologueRepository extends JpaRepository<Psychologue,Integer> {
    @Query("Select c from Conseil c Where c.psychologue.id=:id")
    public List<Conseil> getConseilByPsy(@Param("id") int id);

    @Query("SELECT p FROM Psychologue p WHERE p.status = StatusValidationPsy.VALIDER AND p.etat = true")
     List<Psychologue> getPsychologueValide();

    @Query("SELECT p FROM Psychologue p WHERE p.status = StatusValidationPsy.ENATTENTE AND p.etat = true")
     List<Psychologue> findByStatusEnAttente();

}
