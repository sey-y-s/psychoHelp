package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListConseilDto {

    private String titre;
    private String description;
    private Boolean status;
    private String auteur;
}
