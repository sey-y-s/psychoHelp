package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.entity.Utilisateur;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialiteListeDto {
    private int id;
    private  String nom;
    private String nomAdmin;
}