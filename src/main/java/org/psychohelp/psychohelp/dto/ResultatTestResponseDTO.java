package org.psychohelp.psychohelp.dto;

import lombok.Data;

@Data
public class ResultatTestResponseDTO {
    private Integer id;
    private Integer score;
    private String description;
    private Integer citoyenId;
    private String nomCitoyen; // Optionnel : si tu veux afficher le nom
    private Integer testId;
    private String nomTest;
}