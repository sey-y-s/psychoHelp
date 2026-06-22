package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Utilisateur;

public interface AuthentificationService {
    Utilisateur inscrireUtilisateur (Utilisateur utilisateur);
    Utilisateur connecter (String mail, String motDePasse);
}
