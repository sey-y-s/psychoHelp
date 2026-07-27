package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CitoyenRendezVousResponseDTO {
    private int id;

    private String nomPsychologue;
    private String prenomPsychologue;
    private String specialite;

    private LocalDate dateRdv;

    private LocalTime heureDebut;
    private LocalTime heureFin;

    private StatutRdvEnum statut;

}
