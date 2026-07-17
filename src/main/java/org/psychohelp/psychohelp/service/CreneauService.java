package org.psychohelp.psychohelp.service;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.dto.DateRdvPourCitoyen;
import org.psychohelp.psychohelp.dto.UpdateCreneauDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;

import java.util.List;

public interface CreneauService {

    CreneauResponseDTO creer(CreneauDTO dto, Utilisateur utilisateurConnecte);

    List<CreneauResponseDTO> getAll();

    CreneauResponseDTO getById(int id);

    CreneauResponseDTO update(int id, UpdateCreneauDTO dto);

    void delete(int id);

    List<CreneauResponseDTO> getMesCreneaux(Utilisateur utilisateurConnecte);


    List<CreneauResponseDTO> getDisponibles();

    List<CreneauResponseDTO> getDisponiblesByPsychologueId(Integer psychologueId);
    List<DateRdvPourCitoyen>getToutesLesDatesDisponibles(int p);
}
