package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.CreneauDTO;
import org.psychohelp.psychohelp.dto.CreneauResponseDTO;
import org.psychohelp.psychohelp.dto.UpdateCreneauDTO;
import java.util.List;

public interface CreneauService {

    CreneauResponseDTO creer(CreneauDTO dto);

    List<CreneauResponseDTO> getAll();

    CreneauResponseDTO getById(Long id);

    CreneauResponseDTO update(Long id, UpdateCreneauDTO dto);

    void delete(Long id);


    List<CreneauResponseDTO> getDisponibles();

    List<CreneauResponseDTO> getDisponiblesByPsychologueId(Integer psychologueId);
}
