package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.CategorieTest;

import java.util.List;

public interface CategorieService {
    CategorieTest creerCategorie(CategorieTest categorie);
    List<CategorieTest> obtenirToutesLesCategories();
    CategorieTest modifierCategorie(Long id, CategorieTest categorieMiseAJour);
    void supprimerCategorie(Long id);
}
