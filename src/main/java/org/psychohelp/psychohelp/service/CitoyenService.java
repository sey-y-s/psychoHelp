package org.psychohelp.psychohelp.service;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.CitoyenListeDto;
import org.psychohelp.psychohelp.dto.CitoyenRequestDto;
import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.psychohelp.psychohelp.exceptions.NotFoundException;
import org.psychohelp.psychohelp.repository.CitoyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitoyenService {
    @Autowired
    private CitoyenRepository citoyenRepository;
    public Citoyen ajouterCitoyen(CitoyenRequestDto citoyenRequestDto) {
        Citoyen citoyen = new Citoyen();
        citoyen.setNom(citoyenRequestDto.getNom());
        citoyen.setPrenom(citoyenRequestDto.getPrenom());
        citoyen.setTelephone(citoyenRequestDto.getTelephone());
        citoyen.setMail(citoyenRequestDto.getMail());
        citoyen.setMotDePasse(citoyenRequestDto.getMotDePasse());
        citoyen.setRole(RoleEnum.CITOYEN);
        return citoyenRepository.save(citoyen);
    }

    public CitoyenListeDto modifierCitoyen(CitoyenRequestDto citoyenRequestDto, HttpSession session) {
        Utilisateur utilisateur=(Utilisateur)session.getAttribute("UtilisateurConnecte");
        Citoyen citoyenApartirDeIdSession=citoyenRepository.getByCitoyenId(utilisateur.getId());
        citoyenApartirDeIdSession.setNom(citoyenRequestDto.getNom());
        citoyenApartirDeIdSession.setPrenom(citoyenRequestDto.getPrenom());
        citoyenApartirDeIdSession.setTelephone(citoyenRequestDto.getTelephone());
        citoyenApartirDeIdSession.setMail(citoyenRequestDto.getMail());
        citoyenApartirDeIdSession.setMotDePasse(citoyenRequestDto.getMotDePasse());
        Citoyen citoyenModifier=citoyenRepository.save(citoyenApartirDeIdSession);
        return new CitoyenListeDto(citoyenModifier.getId(),citoyenModifier.getNom(),citoyenModifier.getPrenom(),citoyenModifier.getTelephone(),citoyenModifier.getMail(),citoyenModifier.getRole().toString());

    }


    public CitoyenListeDto getCitoyenById(int id) {
        Citoyen citoyen = citoyenRepository.getByCitoyenId(id);
        if(citoyen==null){
            throw new  NotFoundException("ce citoyen n'existe pas");
        }
        return new CitoyenListeDto(citoyen.getId(),citoyen.getNom(),citoyen.getPrenom(),citoyen.getTelephone(),citoyen.getMail(),citoyen.getRole().toString());

    }
    public List<Citoyen> getAllCitoyen() {
        return citoyenRepository.findAll();
    }
    public List<CitoyenSeanceWithPsychologueDto> listeSeanceWithIsPsychologue(int id) {
        return citoyenRepository.listeSeanceWithIsPsychologue(id);
    }


}
