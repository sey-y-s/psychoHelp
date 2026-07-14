package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class DateRdvPourCitoyen {
    private String jours;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long creneauId;
    private int psyId;
    private String nomPsychologue;

}
