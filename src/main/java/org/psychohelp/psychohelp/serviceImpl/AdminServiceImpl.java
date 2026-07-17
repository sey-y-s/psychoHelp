package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.AdminResponseDTO;
import org.psychohelp.psychohelp.dto.ConseilAfficheDto;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.entity.*;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.enumeration.TypeNotificationEnum;
import org.psychohelp.psychohelp.repository.AdminRepository;
import org.psychohelp.psychohelp.repository.ConseilRepository;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ConseilRepository conseilRepository;

    @Autowired
    private PsychologueRepository psychologueRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private NotificationService notificationService;

//    @Autowired
//    private TestService testService;
//
//    @Autowired
//    private QuestionsTestService questionsTestService;


    @Override
    public AdminResponseDTO ajouterAdmin(AdminDTO dto) {

        Admin admin = new Admin();

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setMail(dto.getMail());
        admin.setTelephone(dto.getTelephone());
        admin.setMotDePasse(dto.getMotDePasse());

        admin.setRole(RoleEnum.ADMIN);
        admin.setDateCreation(LocalDate.now());

        Admin adminSauvegarde = adminRepository.save(admin);

        AdminResponseDTO response = new AdminResponseDTO();
        response.setId(adminSauvegarde.getId());
        response.setNom(adminSauvegarde.getNom());
        response.setPrenom(adminSauvegarde.getPrenom());
        response.setMail(adminSauvegarde.getMail());
        response.setTelephone(adminSauvegarde.getTelephone());
        response.setRole(adminSauvegarde.getRole().name());

        return response;
    }

    @Override
    public AdminResponseDTO  modifierAdmin(Integer id, AdminDTO dto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setMail(dto.getMail());
        admin.setTelephone(dto.getTelephone());

        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            admin.setMotDePasse(dto.getMotDePasse());
        }

        admin.setRole(RoleEnum.ADMIN);

        Admin adminModifie = adminRepository.save(admin);

        AdminResponseDTO response = new AdminResponseDTO();
        response.setId(adminModifie.getId());
        response.setNom(adminModifie.getNom());
        response.setPrenom(adminModifie.getPrenom());
        response.setMail(adminModifie.getMail());
        response.setTelephone(adminModifie.getTelephone());
        response.setRole(adminModifie.getRole().name());

        return response;
    }


    @Override
    public Admin getAdminById(Integer id) {

        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {

        return adminRepository.findAll()
                .stream()
                .map(admin -> {
                    AdminResponseDTO dto = new AdminResponseDTO();

                    dto.setId(admin.getId());
                    dto.setNom(admin.getNom());
                    dto.setPrenom(admin.getPrenom());
                    dto.setMail(admin.getMail());
                    dto.setTelephone(admin.getTelephone());
                    dto.setRole(admin.getRole().name());

                    return dto;
                })
                .toList();
    }
    @Override
    public void supprimerAdmin(Integer id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));

        adminRepository.delete(admin);
    }

    @Override
    public ConseilAfficheDto validerConseil(Integer conseilId) {

        Conseil conseil = conseilRepository.findById(conseilId)
                .orElseThrow(() ->
                        new RuntimeException("Conseil introuvable"));

        conseil.setStatus(true);

        Conseil conseilSauvegarde = conseilRepository.save(conseil);
        notificationService.envoyer(
                conseilSauvegarde.getPsychologue(),
                "Conseil validé",
                "Votre conseil \"" + conseilSauvegarde.getTitre()
                        + "\" a été validé par l'administrateur.",
                TypeNotificationEnum.CONSEIL
        );
        ConseilAfficheDto response = new ConseilAfficheDto();

        response.setTitre(conseilSauvegarde.getTitre());
        response.setDescription(conseilSauvegarde.getDescription());
        response.setAuteur(conseilSauvegarde.getAuteur());

        if (conseilSauvegarde.getPsychologue() != null) {
            response.setPsyNom(
                    conseilSauvegarde.getPsychologue().getNom() + " "
                            + conseilSauvegarde.getPsychologue().getPrenom()
            );
        }

        return response;
    }

    @Override
    public ConseilAfficheDto annulerConseil(Integer conseilId) {

        Conseil conseil = conseilRepository.findById(conseilId)
                .orElseThrow(() ->
                        new RuntimeException("Conseil introuvable"));

        conseil.setStatus(false);


        Conseil conseilSauvegarde = conseilRepository.save(conseil);
        notificationService.envoyer(
                conseilSauvegarde.getPsychologue(),
                "Conseil refusé",
                "Votre conseil \"" + conseilSauvegarde.getTitre()
                        + "\" n'a pas été validé.",
                TypeNotificationEnum.CONSEIL
        );

        ConseilAfficheDto response = new ConseilAfficheDto();

        response.setTitre(conseilSauvegarde.getTitre());
        response.setDescription(conseilSauvegarde.getDescription());
        response.setAuteur(conseilSauvegarde.getAuteur());

        if (conseilSauvegarde.getPsychologue() != null) {
            response.setPsyNom(
                    conseilSauvegarde.getPsychologue().getNom() + " "
                            + conseilSauvegarde.getPsychologue().getPrenom()
            );
        }

        return response;
    }

    @Override
    public  PsychologueListeDto validerInscriptionPsy(Integer id) {

        Psychologue psychologue = psychologueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        psychologue.setStatus(true);
        Psychologue psySauvegarde = psychologueRepository.save(psychologue);

        emailService.envoyerCompteActif(
                psySauvegarde.getMail(),
                psySauvegarde.getPrenom(),
                psySauvegarde.getNom()
        );

        PsychologueListeDto response = new PsychologueListeDto();

        response.setId(psySauvegarde.getId());
        response.setNom(psySauvegarde.getNom());
        response.setPrenom(psySauvegarde.getPrenom());
        response.setTelephone(psySauvegarde.getTelephone());
        response.setMail(psySauvegarde.getMail());
        response.setRole(psySauvegarde.getRole());
        response.setDateCreation(psySauvegarde.getDateCreation());
        response.setStatus(psySauvegarde.getStatus());
        response.setDescription(psySauvegarde.getDescription());
        response.setDiplome_path(psySauvegarde.getDiplome_path());
        response.setCv_path(psySauvegarde.getCv_path());
        response.setEtat(psySauvegarde.getEtat());

        return response;
    }

    @Override
    public PsychologueListeDto annulerInscriptionPsy(Integer id) {

        Psychologue psychologue = psychologueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        psychologue.setStatus(false);

        Psychologue psySauvegarde = psychologueRepository.save(psychologue);

        PsychologueListeDto response = new PsychologueListeDto();

        response.setId(psySauvegarde.getId());
        response.setNom(psySauvegarde.getNom());
        response.setPrenom(psySauvegarde.getPrenom());
        response.setTelephone(psySauvegarde.getTelephone());
        response.setMail(psySauvegarde.getMail());
        response.setRole(psySauvegarde.getRole());
        response.setDateCreation(psySauvegarde.getDateCreation());
        response.setStatus(psySauvegarde.getStatus()); // ou isStatus() selon ton entité
        response.setDescription(psySauvegarde.getDescription());
        response.setDiplome_path(psySauvegarde.getDiplome_path());
        response.setCv_path(psySauvegarde.getCv_path());
        response.setEtat(psySauvegarde.getEtat()); // ou isEtat() selon ton entité

        return response;
    }


}

