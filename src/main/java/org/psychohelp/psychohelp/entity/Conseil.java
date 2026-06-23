package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "conseils")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conseil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    private String titre;

    private String description;

    //le conseil doit etre valider par l'admin
    private Boolean status = false;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(length = 40)
    private String auteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psy_id")
    private Psychologue psychologue;

}
