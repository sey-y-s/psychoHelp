package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.repository.UtilisateurRepository;
import org.psychohelp.psychohelp.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur creer(Utilisateur utl) {
        return utilisateurRepository.save(utl);
    }

    @Override
    public List<Utilisateur> listeUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur utilisateurParId(Integer id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
    }

    @Override
    public Utilisateur modifier(int id, Utilisateur utl) {
        Utilisateur lastUtl = utilisateurParId(id);
        //lastUtl.setId(utl.getId());
        lastUtl.setNom(utl.getNom());
        lastUtl.setPrenom(utl.getPrenom());
        lastUtl.setMail(utl.getMail());
        lastUtl.setTelephone(utl.getTelephone());
        lastUtl.setMotDePasse(utl.getMotDePasse());
        lastUtl.setRole(utl.getRole());
        lastUtl.setDateCreation(utl.getDateCreation());

        utilisateurRepository.save(lastUtl);
        return utl;
    }

    @Override
    public void supUtilisateur(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public List<Utilisateur> getRecentUsers(){
        return utilisateurRepository.findTop5ByOrderByDateCreationDesc();
    }

    @Override
    public long count() {
        return utilisateurRepository.count();
    }
}
