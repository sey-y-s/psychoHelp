package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.StatusValidationPsy;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychologueListeDto {
    private int id;
    private String nom,prenom,telephone,mail;
    private RoleEnum role;
    private LocalDate dateCreation;
    private StatusValidationPsy status;
    private String description;
    private String diplomePath;
    private String cvPath;
    private boolean etat;
    private String specialite;
}
