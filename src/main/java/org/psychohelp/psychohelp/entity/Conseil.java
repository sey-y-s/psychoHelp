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
    private int id;

    @Column(length = 40)
    private String titre;


    private String description;

    private boolean status;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(length = 40)
    private String auteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psy_id")
    private Psychologue psychologue;






}
