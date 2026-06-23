package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatutRdvEnum;

import java.time.LocalDate;
import java.time.LocalTime;
@Data @AllArgsConstructor @NoArgsConstructor
public class CitoyenSeanceWithPsychologueDto {
    private String nomPsychologue;
    private LocalDate dateJourMeme,datePriseRdv;
    private StatutRdvEnum statuRdv;
    private LocalTime heuredebut,heurfin;
    private String jourcrenaux;
}
