package org.psychohelp.psychohelp.serviceImpl;

import org.springframework.transaction.annotation.Transactional;
import org.psychohelp.psychohelp.dto.ConseilAfficheDto;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.repository.ConseilRepository;
import org.psychohelp.psychohelp.service.ConseilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConseilServiceImpl implements ConseilService {

    @Autowired
    private ConseilRepository conseilRepository;

    @Override
    public Conseil creer(Conseil utl) {
        return conseilRepository.save(utl);
    }

    @Override
    public List<ConseilAfficheDto> listeConseil() {

        return conseilRepository.trouverTousAvecPsychologue()
                .stream()
                .map(conseil -> new ConseilAfficheDto(
                        conseil.getTitre(),
                        conseil.getDescription(),
                        conseil.getAuteur(),
                        conseil.getPsychologue().nomComplet()
                ))
                .toList();
    }

    @Override
    public Conseil conseilParId(Integer id) {
        return conseilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conseil non trouvé avec l'id : " + id));
    }

    @Override
    public Conseil modifier(int id, Conseil utl) {
        Conseil lastUtl = conseilParId(id);
        //lastUtl.setId(utl.getId());
        lastUtl.setTitre(utl.getTitre());
        lastUtl.setDescription(utl.getDescription());
        lastUtl.setStatus(utl.getStatus());

        lastUtl.setDatePublication(utl.getDatePublication());
        lastUtl.setAuteur(utl.getAuteur());

        conseilRepository.save(lastUtl);
        return utl;
    }

    @Override
    public void supConseil(Integer id) {
        conseilRepository.deleteById(id);
    }

    @Override
    public List<ConseilAfficheDto> listConseilParStatus(Boolean status) {

        return conseilRepository.trouverParStatutAvecPsychologue(status)
                .stream()
                .map(conseil -> new ConseilAfficheDto(
                        conseil.getTitre(),
                        conseil.getDescription(),
                        conseil.getAuteur(),
                        conseil.getPsychologue().nomComplet()
                ))
                .toList();
    }


}
