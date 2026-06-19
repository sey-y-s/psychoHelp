package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecialiteRepo extends JpaRepository<Specialite,Integer> {
    public void saveSpecialite(Specialite specialite);
    public List<Specialite> findAllSpecialite();
    public Specialite findSpecialiteById(int id);
    public void deleteSpecialiteById(int id);
    //public List<Specialite> findSpecialiteByPsychologueId(int psychologueId);




}
