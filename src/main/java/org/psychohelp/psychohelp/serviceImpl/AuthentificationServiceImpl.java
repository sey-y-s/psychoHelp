package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.repository.UtilisateurRepository;
import org.psychohelp.psychohelp.service.AuthentificationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Transactional
public class AuthentificationServiceImpl implements AuthentificationService {
    private final UtilisateurRepository utilisateurRepository;

    public AuthentificationServiceImpl (UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }
    @Override
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        Optional<Utilisateur> existe = utilisateurRepository.findByMail(utilisateur.getMail());

        if(existe.isPresent()){
            throw new RuntimeException("Cette adresse email est déjà utilisée !");
        }
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur connecter(String mail, String motDePasse) {
        Utilisateur utilisateur = utilisateurRepository.findByMail(mail).orElseThrow(()
                -> new RuntimeException("Identifiants incorrects (email introuvable)."));

        if(!utilisateur.getMotDePasse().equals(motDePasse)){
            throw new RuntimeException("Identifiants incorrects (mot de passe invalide).");
        }

        return utilisateur;
    }
}
