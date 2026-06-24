package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.dto.UpdateSpecialiteDto;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.repository.SpecialiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecialiteService {
    @Autowired
    private SpecialiteRepo specialiteRepo;
    public void ajouter(UpdateSpecialiteDto updateSpecialiteDto) {
        Specialite specialite=new Specialite();
        specialite.setNom(updateSpecialiteDto.getNom());

        specialiteRepo.save(specialite);
    }
    public List<Specialite> ListeSpecialite(){
        return specialiteRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
    public SpecialiteListeDto updateSpecialite(int id,UpdateSpecialiteDto updateSpecialiteDto){
        Specialite specialite=specialiteRepo.findById(id).orElseThrow(()->new RuntimeException("cette specialite est introuvable"));
        specialite.setNom(updateSpecialiteDto.getNom());
        Specialite specialitemodif =specialiteRepo.save(specialite);
        return new SpecialiteListeDto(specialitemodif.getId(),specialitemodif.getNom());

    }
}
