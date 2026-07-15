package org.psychohelp.psychohelp.service;
import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.dto.RequestSpecialiteDto;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.entity.Specialite;

import java.util.List;

public interface SpecialiteService {
    void ajouter(RequestSpecialiteDto updateSpecialiteDto, HttpSession session);

    List<Specialite> listeSpecialite(HttpSession session);

    SpecialiteListeDto getSpecialite(int id, HttpSession session);

    void supprimer(int id, HttpSession session);

    List<Psychologue> getSpecialiteIsPsycholoque(int id);

    SpecialiteListeDto updateSpecialite(int id, RequestSpecialiteDto updateSpecialiteDto, HttpSession session);

    List<Specialite> listePublique();

}
