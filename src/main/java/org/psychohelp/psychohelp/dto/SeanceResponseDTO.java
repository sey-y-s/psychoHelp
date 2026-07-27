package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceResponseDTO {

    private int id;
    private LocalDate dateRdv;
    private StatutRdvEnum statut;

    private String nomCitoyen;
    private String prenomCitoyen;

    private String jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
}
