package org.psychohelp.psychohelp.service;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.CitoyenListeDto;
import org.psychohelp.psychohelp.dto.CitoyenRequestDto;
import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Citoyen;

import java.util.List;

public interface CitoyenService {
    Citoyen ajouterCitoyen(CitoyenRequestDto citoyenRequestDto);

    CitoyenListeDto modifierCitoyen(CitoyenRequestDto citoyenRequestDto, HttpSession session);

    CitoyenListeDto getCitoyenById(int id);

    List<Citoyen> getAllCitoyen();

    List<CitoyenSeanceWithPsychologueDto> listeSeanceWithIsPsychologue(int id);
}
