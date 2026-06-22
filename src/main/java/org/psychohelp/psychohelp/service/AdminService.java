package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.entity.Conseil;

import java.util.List;

public interface AdminService {

    Admin ajouterAdmin(AdminDTO dto);

    Admin modifierAdmin(Integer id, AdminDTO dto);

    Admin getAdminById(Integer id);

    List<Admin> getAllAdmins();

    Conseil validerConseil(Integer conseilId);

    Conseil annulerConseil(Integer conseilId);

    CategorieTest ajouterCategorie(CategorieTest categorie);

    List<CategorieTest> obtenirToutesLesCategories();

    CategorieTest modifierCategorie(Integer id,
                                    CategorieTest categorie);

    void supprimerCategorie(Integer id);
}
