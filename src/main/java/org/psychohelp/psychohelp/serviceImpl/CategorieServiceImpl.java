package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.repository.CategorieRepository;
import org.psychohelp.psychohelp.service.CategorieService;

import java.util.List;

public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    public CategorieServiceImpl(CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
    }
    @Override
    public CategorieTest creerCategorie(CategorieTest categorie) {
     return  categorieRepository.save(categorie);
    }

    @Override
    public List<CategorieTest> obtenirToutesLesCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public CategorieTest modifierCategorie(Integer id, CategorieTest categorieMiseAJour) {
       CategorieTest categorie = categorieRepository.findById(Long.valueOf(id)).orElseThrow(
               () -> new RuntimeException("Categorie introuvable avec l'ID:" +id));

       categorie.setNomCategorie(categorieMiseAJour.getNomCategorie());
       categorie.setDescription(categorieMiseAJour.getDescription());
        return categorieRepository.save(categorie);
    }

    @Override
    public void supprimerCategorie(Integer id) {
        if(!categorieRepository.existsById(Long.valueOf(id))){
            throw new RuntimeException("Categorie introuvablle");
        }
        categorieRepository.deleteById(Long.valueOf(id));
    }
}
