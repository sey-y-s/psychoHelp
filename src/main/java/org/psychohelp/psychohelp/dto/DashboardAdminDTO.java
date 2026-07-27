package org.psychohelp.psychohelp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.psychohelp.psychohelp.entity.Utilisateur;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardAdminDTO {
    private long TotalUtilisateur;
    private long TotalRdv;
    private long TotalConseil;
    private long TotalTest;

    private List<UtilisateurListDTO> utilisateursRecent;
}