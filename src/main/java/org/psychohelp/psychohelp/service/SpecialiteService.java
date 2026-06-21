package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.repository.SpecialiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecialiteService {
    @Autowired
    private SpecialiteRepo specialiteRepo;
    public Specialite ajouter(Specialite specialite){
        return specialiteRepo.save(specialite);
    }
    public List<Specialite> ListeSpecialite(){
        return specialiteRepo.findAll();
    }
    public Specialite getSpecialite(int id){
        return specialiteRepo.findById(id).orElse(null);
    }
    public void supprimer(int id){
        specialiteRepo.deleteById(id);
    }
    public List<Psychologue>getSpecialiteIsPsycholoque(int id){
        return specialiteRepo.getSpecialiteIsPsychologue(id);
    }
}
