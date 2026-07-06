package org.psychohelp.psychohelp.serviceImpl;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.*;
import org.psychohelp.psychohelp.entity.*;
import org.psychohelp.psychohelp.exceptions.AccesRefuseException;
import org.psychohelp.psychohelp.exceptions.NotFoundException;
import org.psychohelp.psychohelp.repository.SpecialiteRepo;
import org.psychohelp.psychohelp.service.SpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecialiteServiceImpl implements SpecialiteService {
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


    public List<Specialite> listeSpecialite( HttpSession session) {
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        return specialiteRepo.getSpecialiteByAdmin_Id(utilisateur.getId());
    }
    public SpecialiteListeDto getSpecialite(int id, HttpSession session) {
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        Specialite specialite=specialiteRepo.getSpecialiteById(id);
        if(specialite==null){
            throw new NotFoundException("Le specialite n'existe pas");
        }
        if(specialite.getAdmin().getId()!=utilisateur.getId()){
            throw new AccesRefuseException("cette specialité ne vous appartient pas");
        }

        return new SpecialiteListeDto(specialite.getId(),specialite.getNom(),specialite.getAdmin().getNom());
    }
    public void supprimer(int id,HttpSession session){
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        Specialite specialite=specialiteRepo.getSpecialiteById(id);
        if(specialite.getAdmin().getId()!=utilisateur.getId()){
            throw new AccesRefuseException("cette specialité ne vous appartient pas");
        }
        if(specialiteRepo.specialiteIsUsed(specialite.getId())>0){
            throw new AccesRefuseException("vous ne pouvez pas supprimer cette specialite cars elle est affectée à au moins un psychologue");
        }
        specialiteRepo.deleteById(id);
    }

    public List<Psychologue>getSpecialiteIsPsycholoque(int id){

        return specialiteRepo.getSpecialiteIsPsychologue(id);
    }
    public SpecialiteListeDto updateSpecialite(int id, RequestSpecialiteDto updateSpecialiteDto, HttpSession session) {

        Specialite specialite=specialiteRepo.getSpecialiteById(id);
        if(specialite==null){
            throw new NotFoundException("Specialite n'existe pas");
        }
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");

        if(specialite.getAdmin().getId()!=utilisateur.getId()){
            throw new AccesRefuseException("cette specialité ne vous appartient pas");
        }
        specialite.setNom(updateSpecialiteDto.getNom());
        Specialite specialitemodif =specialiteRepo.save(specialite);

        return new SpecialiteListeDto(specialitemodif.getId(),specialitemodif.getNom(),specialitemodif.getAdmin().getNom());

    }
}
