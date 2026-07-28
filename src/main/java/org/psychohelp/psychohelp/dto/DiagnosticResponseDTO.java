package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticResponseDTO {
    private Integer id;
    private String message;
    private String niveau;
    private Integer scoreMax;
    private Integer scoreMin;
    private Integer testId;
}
