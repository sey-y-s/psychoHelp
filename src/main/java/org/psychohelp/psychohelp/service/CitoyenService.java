package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.CitoyenSeanceWithPsychologueDto;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.repository.CitoyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitoyenService {
    @Autowired
    private CitoyenRepository citoyenRepository;
    public Citoyen ajouterCitoyen(Citoyen citoyen) {
        return citoyenRepository.save(citoyen);
    }
    public Citoyen getCitoyenById(int id) {
        return citoyenRepository.findById(id).orElse(null);
    }
    public List<Citoyen> getAllCitoyen() {
        return citoyenRepository.findAll();
    }
    public List<CitoyenSeanceWithPsychologueDto> listeSeanceWithIsPsychologue(int id) {


        return citoyenRepository.listeSeanceWithIsPsychologue(id);
    }


}
