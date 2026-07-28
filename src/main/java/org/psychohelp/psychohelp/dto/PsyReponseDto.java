package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsyReponseDto {

    private String nom,prenom,telephone,mail;
    private String description;
    private String diplomePath;
    private String cvPath;

}
