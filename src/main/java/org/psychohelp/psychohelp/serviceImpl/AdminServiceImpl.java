package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.AdminDTO;
import org.psychohelp.psychohelp.dto.PsychologueListeDto;
import org.psychohelp.psychohelp.entity.Admin;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.repository.AdminRepository;
import org.psychohelp.psychohelp.repository.ConseilRepository;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ConseilRepository conseilRepository;

    @Override
    public Admin ajouterAdmin(AdminDTO dto) {

        Admin admin = new Admin();

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setMail(dto.getEmail());
        admin.setTelephone(dto.getTelephone());

        admin.setDateCreation(LocalDate.now());

        return adminRepository.save(admin);
    }

    @Override
    public Admin modifierAdmin(Integer id, AdminDTO dto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));

        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setMail(dto.getEmail());
        admin.setTelephone(dto.getTelephone());

        return adminRepository.save(admin);
    }


    @Override
    public Admin getAdminById(Integer id) {

        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin introuvable"));
    }

    @Override
    public List<Admin> getAllAdmins() {

        return adminRepository.findAll();
    }

    @Override
    public Conseil validerConseil(Integer conseilId) {

        Conseil conseil = conseilRepository.findById(conseilId)
                .orElseThrow(() ->
                        new RuntimeException("Conseil introuvable"));

        conseil.setStatus(true);

        return conseilRepository.save(conseil);
    }

    @Override
    public Conseil annulerConseil(Integer conseilId) {

        Conseil conseil = conseilRepository.findById(conseilId)
                .orElseThrow(() ->
                        new RuntimeException("Conseil introuvable"));

        conseil.setStatus(false);

        return conseilRepository.save(conseil);
    }
}
