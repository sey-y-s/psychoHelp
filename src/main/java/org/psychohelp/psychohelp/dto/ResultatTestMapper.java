package org.psychohelp.psychohelp.dto;

import org.psychohelp.psychohelp.entity.ResultatTest;

public class ResultatTestMapper {

    public static ResultatTestResponseDTO toDTO(ResultatTest entite) {
        if (entite == null) return null;

        ResultatTestResponseDTO dto = new ResultatTestResponseDTO();
        dto.setId(entite.getId());
        dto.setScore(entite.getScore());
        dto.setDescription(entite.getDescription());

        if (entite.getCitoyen() != null) {
            // Attention : ton repository utilise des Integer pour l'ID citoyen, assure-toi de la cohérence de types
            dto.setCitoyenId(Math.toIntExact(entite.getCitoyen().getId()));
            dto.setNomCitoyen(entite.getCitoyen().getNom()); // s'il y a un champ nom
        }

        if (entite.getTest() != null) {
            dto.setTestId(entite.getTest().getId());
            dto.setNomTest(entite.getTest().getNom_test());
        }

        return dto;
    }
}