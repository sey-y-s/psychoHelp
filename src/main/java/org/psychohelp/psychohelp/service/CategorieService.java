package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.CategorieReponseDTO;
import org.psychohelp.psychohelp.dto.CategorieRequestDTO;

import java.util.List;

public interface CategorieService {
    CategorieReponseDTO creerCategorie(CategorieRequestDTO categorie);
    List<CategorieReponseDTO> obtenirToutesLesCategories();
    CategorieReponseDTO modifierCategorie(Integer id, CategorieRequestDTO categorieMiseAJour);
    void supprimerCategorie(Integer id);
    CategorieReponseDTO obtenirCategorieParId(Integer id);
    CategorieReponseDTO getCategorieByTestId(Integer id);
}
