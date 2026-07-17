package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;

import java.time.LocalDate;

@Entity
@Table(name = "conseils")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conseil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40)
    private String titre;

    private String description;

    //le conseil doit etre valider par l'admin
    //@Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Boolean status=false;

    @Column(name = "date_publication")
    private LocalDate datePublication=LocalDate.now();

    @Column(length = 40)
    private String auteur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psy_id")
    private Psychologue psychologue;



}
