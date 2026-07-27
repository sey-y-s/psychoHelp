package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatusValidationPsy;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEtatStatusDto {
    private StatusValidationPsy status;
private Boolean etat;
}
