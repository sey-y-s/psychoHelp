package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.AdminResponseDTO;
import org.psychohelp.psychohelp.dto.ConseilAfficheDto;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.entity.*;

import java.util.List;

public interface AdminService {

    AdminResponseDTO  ajouterAdmin(AdminDTO dto);

    AdminResponseDTO  modifierAdmin(Integer id, AdminDTO dto);

    Admin getAdminById(Integer id);

    List<AdminResponseDTO> getAllAdmins();

    void supprimerAdmin(Integer id);

    ConseilAfficheDto validerConseil(Integer conseilId, Integer adminId);

    ConseilAfficheDto annulerConseil(Integer conseilId, Integer adminId);

    PsychologueListeDto validerInscriptionPsy (Integer id);

    PsychologueListeDto annulerInscriptionPsy(Integer id);
    List<PsychologueListeDto> listerPsychologuesEnAttente();


}
