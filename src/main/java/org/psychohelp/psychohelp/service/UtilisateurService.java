package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    public Utilisateur creer(Utilisateur utl){
        return utilisateurRepository.save(utl);
    }

    public List<Utilisateur> listeUtilisateur(){
        return utilisateurRepository.findAll();
    }
    public Utilisateur utilisateurParId(Integer id){
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
    }

    public Utilisateur modifier(int id, Utilisateur utl){
        Utilisateur lastUtl = utilisateurParId(id);
        //lastUtl.setId(utl.getId());
        lastUtl.setNom(utl.getNom());
        lastUtl.setPrenom(utl.getPrenom());
        lastUtl.setMail(utl.getMail());
        lastUtl.setTelephone(utl.getTelephone());
        lastUtl.setMotDePasse(utl.getMotDePasse());
        lastUtl.setRole(utl.getRole());
        lastUtl.setDateCreation(utl.getDateCreation());

        utilisateurRepository.save(utl);
        return utl;
    }

    public void supUtilisateur(Integer id){
        utilisateurRepository.deleteById(id);
    }
}
