package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitoyenRepository extends JpaRepository <Citoyen, Integer> {
    //cette requete retourne le redv d'un citoyen en se basant sur son id
    @Query("select new org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto(py.nom,se.date,se.dateRdv,se.statut,cre.heureDebut,cre.heureFin,cre.jours)" +
            "from Seance se " +
            "join se.citoyen ci " +
            "join se.creneau cre " +
            "join cre.psychologue py where ci.id = :id")
    public List<CitoyenSeanceWithPsychologueDto> listeSeanceWithIsPsychologue(@Param("id") int id);

}
