package org.psychohelp.psychohelp.dto;

import lombok.Data;

import java.time.LocalTime;
@Data
public class CreneauResponseDTO {
    private int id;
    private String jours;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Boolean statut;
    private String nomPsychologue;
}
