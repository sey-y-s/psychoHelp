package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialiteRepo extends JpaRepository<Specialite,Integer> {
    @Query("select p from Psychologue p where p.specialite.id=:id")
    public List<Psychologue> getSpecialiteIsPsychologue(@Param("id") int specialite_id);




}
