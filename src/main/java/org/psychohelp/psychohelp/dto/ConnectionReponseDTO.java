package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionReponseDTO {
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private RoleEnum role;
}
