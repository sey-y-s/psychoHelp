package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.AdminResponseDTO;
import org.psychohelp.psychohelp.entity.*;

import java.util.List;

public interface AdminService {

    Admin ajouterAdmin(AdminDTO dto);

    Admin modifierAdmin(Integer id, AdminDTO dto);

    Admin getAdminById(Integer id);

    List<AdminResponseDTO> getAllAdmins();

    void supprimerAdmin(Integer id);

    Conseil validerConseil(Integer conseilId);

    Conseil annulerConseil(Integer conseilId);

    Psychologue validerInscriptionPsy(Integer id);

    Psychologue annulerInscriptionPsy(Integer id);


}
