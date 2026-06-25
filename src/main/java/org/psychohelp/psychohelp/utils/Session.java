package org.psychohelp.psychohelp.utils;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.exceptions.AccesRefuseException;

public final class Session {

    public  Session(){

    }
    public static Utilisateur utilisateur(HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");

        if(utilisateur==null){
            throw new RuntimeException("Veuillez vous connecter svp");
        }
        return utilisateur;
    }

    public static void verifierRole(HttpSession session, RoleEnum role){
        Utilisateur utilisateur = utilisateur(session);
        if(!utilisateur.getRole().equals(role)){
            throw  new AccesRefuseException("Accès refusé : Vous n'avez pas le rôle requis.");

        }
    }
}
