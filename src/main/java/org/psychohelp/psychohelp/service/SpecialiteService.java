package org.psychohelp.psychohelp.service;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.dto.RequestSpecialiteDto;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.repository.SpecialiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecialiteService {
    @Autowired
    private SpecialiteRepo specialiteRepo;
    public void ajouter(RequestSpecialiteDto updateSpecialiteDto, HttpSession session) {
        Admin admin=new Admin();
        Specialite specialite=new Specialite();
        specialite.setNom(updateSpecialiteDto.getNom());
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        admin.setId(utilisateur.getId());
        specialite.setAdmin(admin);

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
    public SpecialiteListeDto updateSpecialite(int id, RequestSpecialiteDto updateSpecialiteDto, HttpSession session) {
        Specialite specialite=specialiteRepo.findById(id).orElseThrow(()->new RuntimeException("cette specialite est introuvable"));
        specialite.setNom(updateSpecialiteDto.getNom());
        Specialite specialitemodif =specialiteRepo.save(specialite);
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");

        return new SpecialiteListeDto(specialitemodif.getId(),specialitemodif.getNom(),utilisateur.getNom());

    }
}
