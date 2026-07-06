package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoixMultiplesReponseDTO {
    private Integer id;
    private String choix;
    private int score;
    private String question;
}
