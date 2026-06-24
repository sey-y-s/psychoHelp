package org.psychohelp.psychohelp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.dto.UpdateCreneauDTO;
import org.psychohelp.psychohelp.entity.Creneau;
import org.psychohelp.psychohelp.entity.Psychologue;
import org.psychohelp.psychohelp.repository.CreneauRepository;
import org.psychohelp.psychohelp.repository.PsychologueRepository;
import org.psychohelp.psychohelp.service.CreneauService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreneauServiceImpl implements CreneauService {

    private final CreneauRepository cp;
    private final PsychologueRepository pp;

    @Override
    public CreneauResponseDTO creer(CreneauDTO dto) {

        Psychologue psychologue = pp.findById(dto.getPsychologueId().intValue())
                .orElseThrow(() -> new RuntimeException("Psychologue introuvable"));

        Creneau creneau = new Creneau();

        creneau.setJours(dto.getJours());
        creneau.setHeureDebut(dto.getHeureDebut());
        creneau.setHeureFin(dto.getHeureFin());
        creneau.setStatut(dto.getStatut());
        creneau.setPsychologue(psychologue);

        Creneau saved = cp.save(creneau);

        CreneauResponseDTO response = new CreneauResponseDTO();

        response.setId(saved.getId());
        response.setJours(saved.getJours());
        response.setHeureDebut(saved.getHeureDebut());
        response.setHeureFin(saved.getHeureFin());
        response.setStatut(saved.getStatut());
        response.setNomPsychologue(
                saved.getPsychologue().getNom() + " " +
                        saved.getPsychologue().getPrenom());

        return mapToDTO(saved);
    }

    @Override
    public List<CreneauResponseDTO> getAll() {
        return cp.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public CreneauResponseDTO getById(Long id) {

        Creneau creneau = cp.findById(id)
                .orElseThrow(() -> new RuntimeException("Créneau introuvable"));
        return mapToDTO(creneau);
    }

    @Override
    public CreneauResponseDTO update(Long id, UpdateCreneauDTO dto) {
        Creneau creneau = cp.findById(id)
                .orElseThrow(() -> new RuntimeException("Créneau introuvable"));

        creneau.setJours(dto.getJours());
        creneau.setHeureDebut(dto.getHeureDebut());
        creneau.setHeureFin(dto.getHeureFin());
        creneau.setStatut(dto.getStatut());

        return mapToDTO(cp.save(creneau));
    }

    @Override
    public void delete(Long id) {
        cp.deleteById(id);
    }

    @Override
    public List<CreneauResponseDTO> getDisponibles() {

        return cp.findByStatutTrue()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<CreneauResponseDTO> getDisponiblesByPsychologueId(Integer psychologueId) {

        List<CreneauResponseDTO> creneaux =
                cp.findByPsychologueIdAndStatutTrue(psychologueId)
                        .stream()
                        .map(this::mapToDTO)
                        .toList();

        if (creneaux.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aucun créneau disponible pour ce psychologue"
            );

        }
        return creneaux;
    }

    private CreneauResponseDTO mapToDTO(Creneau creneau) {

        CreneauResponseDTO dto = new CreneauResponseDTO();

        dto.setId(creneau.getId());
        dto.setJours(creneau.getJours());
        dto.setHeureDebut(creneau.getHeureDebut());
        dto.setHeureFin(creneau.getHeureFin());
        dto.setStatut(creneau.getStatut());

        if (creneau.getPsychologue() != null) {
            dto.setNomPsychologue(
                    creneau.getPsychologue().getNom() + " " +
                            creneau.getPsychologue().getPrenom());
        }

        return dto;
    }

}
