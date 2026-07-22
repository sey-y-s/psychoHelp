package org.psychohelp.psychohelp.utils;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.AdminResponseDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.exceptions.AccesRefuseException;
import org.psychohelp.psychohelp.exceptions.ConnexionException;

public final class Session {

   public  Session(){

    }
    public static Utilisateur getUtilisateur(HttpSession session){
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("UtilisateurConnecte");

        if(utilisateur==null){
            throw new ConnexionException("Veuillez vous connecter svp");
        }
        return utilisateur;
    }

    public static void verifierRole(HttpSession session, RoleEnum role){
        Utilisateur utilisateur = getUtilisateur(session);
        if(!utilisateur.getRole().equals(role)){
            throw  new AccesRefuseException("Accès refusé : Vous n'avez pas le rôle requis.");

        }
    }

    public static void verifierRole(HttpSession session, RoleEnum role1, RoleEnum role2){
        Utilisateur utilisateur = getUtilisateur(session);
        if(!(utilisateur.getRole().equals(role1) || utilisateur.getRole().equals(role2))){
            throw  new AccesRefuseException("Accès refusé : Vous n'avez pas le rôle requis.");
        }

    }


    public static AdminResponseDTO getConnectedUser(HttpSession session){
        Utilisateur u = (Utilisateur) session.getAttribute("UtilisateurConnecte");
        if (u==null){return null;}
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(u.getId());
        dto.setNom(u.getNom());
        dto.setPrenom(u.getPrenom());
        dto.setMail(u.getMail());
        dto.setRole(u.getRole().toString());
        return dto;
    }
}
