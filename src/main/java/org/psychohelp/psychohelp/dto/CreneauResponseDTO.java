package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data @AllArgsConstructor @NoArgsConstructor
public class CreneauResponseDTO {
    private int id;
    private String jours;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Boolean statut;
    private String nomPsychologue;
}
