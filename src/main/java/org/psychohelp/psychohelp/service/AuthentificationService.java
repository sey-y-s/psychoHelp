package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.ConnectionDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;

public interface AuthentificationService {
    Utilisateur inscrireUtilisateur (Utilisateur utilisateur);
    Utilisateur connecter (ConnectionDTO connectionDTO);
}
