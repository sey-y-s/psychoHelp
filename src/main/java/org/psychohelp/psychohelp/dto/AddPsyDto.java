package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPsyDto {
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private  String motDePasse;

    private RoleEnum role;
    //private LocalDate dateCreation;
    private Boolean status=false;
    private String description;
    private String diplome_path;
    private String  cv_path;
    private Boolean etat=true;
    private Integer idSpecialite;
}
