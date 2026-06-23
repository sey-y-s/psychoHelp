package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConseilDto {
        private  int id;
        private String titre;
        private String description;
        private Boolean status=false;
        private String auteur;


}
