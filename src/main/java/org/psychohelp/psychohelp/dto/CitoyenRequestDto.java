package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CitoyenRequestDto {
    private String nom;
    private String prenom;
    private String telephone;
    private String mail;
    private  String motDePasse;

}
