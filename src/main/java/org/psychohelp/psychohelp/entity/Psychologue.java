package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name = "psychologues")
@AllArgsConstructor
@NoArgsConstructor
public class Psychologue extends Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto-incremente
    private int id;
    private boolean status;
    private String description;
    private String diplome_path;
    private String  cv_path;
    private boolean etat;


}
