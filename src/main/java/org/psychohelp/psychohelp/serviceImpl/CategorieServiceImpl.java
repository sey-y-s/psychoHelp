package org.psychohelp.psychohelp.serviceImpl;

import org.psychohelp.psychohelp.dto.CategorieReponseDTO;
import org.psychohelp.psychohelp.dto.CategorieRequestDTO;
import org.psychohelp.psychohelp.dto.SpecialiteListeDto;
import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.repository.CategorieRepository;
import org.psychohelp.psychohelp.service.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl
        implements CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(
            CategorieRepository categorieRepository) {

        this.categorieRepository = categorieRepository;
    }

    @Override
    public CategorieReponseDTO creerCategorie(
            CategorieRequestDTO categorie) {
        CategorieTest categorieTest1= new CategorieTest();
        categorieTest1.setNomCategorie(categorie.getNomCategorie());
        categorieTest1.setDescription(categorie.getDescription());
        CategorieTest categorieTest = categorieRepository.save(categorieTest1);

        return new CategorieReponseDTO(categorieTest.getId(), categorieTest.getNomCategorie(), categorieTest.getDescription());
    }

    @Override
    public List<CategorieReponseDTO>
    obtenirToutesLesCategories() {
        return categorieRepository.findAll().stream().map(
                categorie -> new CategorieReponseDTO(categorie.getId(), categorie.getNomCategorie(), categorie.getDescription())
        ).toList();
    }

    @Override
    public CategorieReponseDTO obtenirCategorieParId(
            Integer id) {
        CategorieTest categorieTest = categorieRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Categorie introuvable avec l'ID : "
                                        + id));

        return new CategorieReponseDTO(categorieTest.getId(), categorieTest.getNomCategorie(), categorieTest.getDescription());

    }

    @Override
    public CategorieReponseDTO modifierCategorie(
            Integer id,
            CategorieRequestDTO categorieMiseAJour) {

        CategorieTest categorie =
                categorieRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Categorie introuvable avec l'ID : "
                                                + id));

        categorie.setNomCategorie(
                categorieMiseAJour.getNomCategorie());

        categorie.setDescription(
                categorieMiseAJour.getDescription());
        categorieRepository.save(categorie);

        return new CategorieReponseDTO(categorie.getId(), categorie.getNomCategorie(), categorie.getDescription());
    }

    @Override
    public void supprimerCategorie(
            Integer id) {

        if (!categorieRepository.existsById(id)) {

            throw new RuntimeException(
                    "Categorie introuvable avec l'ID : "
                            + id);
        }

        categorieRepository.deleteById(id);

    }
}