package org.psychohelp.psychohelp.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.Creneau;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeanceDTO {
    private int id;
    private String nom;
    private LocalDate date;
    private LocalDate dateRdv;
    private StatutRdvEnum statut;
    private Citoyen citoyen;
    private Creneau creneau;
}