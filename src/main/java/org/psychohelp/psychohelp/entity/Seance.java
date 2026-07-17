package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;

import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.Creneau;

import java.time.LocalDate;

@Entity
@Table(name = "seances")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date=LocalDate.now();

    @Column(name = "date_rdv")
    private LocalDate dateRdv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatutRdvEnum statut;

    @ManyToOne
    @JoinColumn(name = "citoyen_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Citoyen citoyen;

    @ManyToOne
    @JoinColumn(name = "creneau_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Creneau creneau;
}
