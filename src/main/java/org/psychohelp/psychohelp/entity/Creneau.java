package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "creneaux")
public class Creneau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String jours;
    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;
    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;
    private Boolean statut;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "psychologue_id")
    private Psychologue psychologue;

    @OneToMany(mappedBy = "creneau")
    @JsonManagedReference
    private List<Seance> seances;
}
