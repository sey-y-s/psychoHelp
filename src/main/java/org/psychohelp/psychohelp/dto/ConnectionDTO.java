package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionDTO {

    private String email;

    // Email ou numéro de téléphone
    private String identifiant;

    private String motDePasse;


}
