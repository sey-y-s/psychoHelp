package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeanceDTO {
    private LocalDate dateRdv;
    private StatutRdvEnum statut;
    //private int citoyenId;
    private Long creneauId;
}