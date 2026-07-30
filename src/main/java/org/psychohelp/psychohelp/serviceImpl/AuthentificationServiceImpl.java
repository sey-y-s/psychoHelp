package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import org.psychohelp.psychohelp.dto.ConnectionDTO;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
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
        // Vérifie l'email uniquement s'il est renseigné
        if (utilisateur.getMail() != null && !utilisateur.getMail().trim().isEmpty()) {

            Optional<Utilisateur> existeMail = utilisateurRepository.findByMail(utilisateur.getMail());
            if (existeMail.isPresent()) {
                throw new RuntimeException("Cette adresse email est déjà utilisée !");
            }
        }
        // Vérifie toujours le téléphone
        Optional<Utilisateur> existeTelephone = utilisateurRepository.findByTelephone(utilisateur.getTelephone());
        if (existeTelephone.isPresent()) {
            throw new RuntimeException("Ce numéro de téléphone est déjà utilisé !");
        }

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur connecter(ConnectionDTO connectionDTO) {
        String msg = "Identifiants incorrects.";
        Utilisateur utilisateur;

        String identifiant = connectionDTO.getIdentifiant().trim();

        // Si le texte contient @ on recherche par email
        if (identifiant.contains("@")) {
            utilisateur = utilisateurRepository
                    .findByMail(identifiant)
                    .orElseThrow(() -> new ConnexionException(msg));
        } else {
            // Sinon on recherche par téléphone
            utilisateur = utilisateurRepository
                    .findByTelephone(identifiant)
                    .orElseThrow(() -> new ConnexionException(msg));
        }
        // Vérification du mot de passe
        if (!utilisateur.getMotDePasse().equals(connectionDTO.getMotDePasse())) {
            throw new ConnexionException(msg);
        }
        // Vérification spécifique aux psychologues
        if (utilisateur instanceof Psychologue psychologue) {
            if (psychologue.getStatus().toString().equals("ENATTENTE")) {
                throw new ConnexionException(
                        "Votre compte est en attente de validation par l'administrateur. Veuillez patienter.");
            }
            if (Boolean.FALSE.equals(psychologue.getEtat())) {
                throw new ConnexionException("Ce compte est inactif.");
            }
        }

        return utilisateur;
    }
}
