package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

   // private Integer id;

    private String nom;

    private String prenom;

    private String mail;

    private String telephone;

    private String motDePasse;

   // private String role;

}
