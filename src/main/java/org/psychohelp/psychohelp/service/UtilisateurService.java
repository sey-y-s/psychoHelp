package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    public Utilisateur creer(Utilisateur utl);

    public List<Utilisateur> listeUtilisateur();

    public Utilisateur utilisateurParId(Integer id);

    public Utilisateur modifier(int id, Utilisateur utl);

    public void supUtilisateur(Integer id);

}
