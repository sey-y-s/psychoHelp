package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListConseilDto {

    private String titre;
    private String description;
    private StatusConseilEnum status;
    private String auteur;
}
