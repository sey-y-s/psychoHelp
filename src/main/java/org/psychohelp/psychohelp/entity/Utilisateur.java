package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;

import java.time.LocalDate;
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

    @Column(nullable = false, length = 30)
    private String nom;

    @Column(nullable = false, length = 30)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false, unique = true, length = 20)
    private String telephone;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

   @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @Column(name = "date_creation")
    private LocalDate dateCreation=LocalDate.now();




}
