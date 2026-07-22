package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiagnosticResponseDTO {

    private Integer score;

    private String niveau;

    private String message;
}
