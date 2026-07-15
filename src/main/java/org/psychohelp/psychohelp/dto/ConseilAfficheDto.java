package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConseilAfficheDto {
        private String titre;
        private String description;

        private String auteur;
        private String psyNom;

        private LocalDate datePublication;

        private String status;

        private Integer id;


}
