package org.psychohelp.psychohelp.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UpdateCreneauDTO {
    private String jours;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Boolean statut;
}
