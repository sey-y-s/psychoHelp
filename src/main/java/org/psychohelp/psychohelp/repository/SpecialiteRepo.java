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
    public List<Specialite> getSpecialiteByAdmin_Id(@Param("id") int admin);

    public Specialite getSpecialiteById(Integer id);
    @Query("select count(p.specialite.id) as nbre from Psychologue p where p.specialite.id=:id_specialite")
    public int specialiteIsUsed(@Param("id_specialite") int id_specialite);






}
