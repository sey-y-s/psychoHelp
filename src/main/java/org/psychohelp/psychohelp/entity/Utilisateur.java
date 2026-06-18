package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//specifier que ça correspond a une table de la bdd
@Entity
@Table(name = "utilisateurs")
@Data //getters setters tostring...
//le constructeur par defaut
@NoArgsConstructor
//constructeur avec les parametres
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id //designer comme clef primaire de la table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremente
    private int id;

    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private String motDePasse;
    private String role;
    private Date date_creation;




}
