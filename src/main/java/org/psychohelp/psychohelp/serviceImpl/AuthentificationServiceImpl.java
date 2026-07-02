package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import org.psychohelp.psychohelp.dto.ConnectionDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.exceptions.ConnexionException;
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
    public Utilisateur connecter(ConnectionDTO connectionDTO) {
        String msg = "Identifiants incorrects.";
        Utilisateur utilisateur = utilisateurRepository.findByMail(connectionDTO.getEmail()).orElseThrow(()
                -> new ConnexionException(msg));

        if(!utilisateur.getMotDePasse().equals(connectionDTO.getMotDePasse())){
            throw new ConnexionException(msg);
        }

        return utilisateur;
    }
}
