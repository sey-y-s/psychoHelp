package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "choixmultiple")
public class ChoixMultiple {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id ;
    private String choix ;
    private int score ;
}
