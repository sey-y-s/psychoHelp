package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychologueListeDto {
    private int id;
    private String nom,prenom,telephone,mail;
    private RoleEnum role;
    private LocalDate dateCreation;
    private boolean status;
    private String description;
    private String diplome_path;
    private String  cv_path;
    private boolean etat;
}
