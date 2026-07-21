package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.DiagnosticResponseDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;

public interface DiagnosticService {

    DiagnosticResponseDTO genererDiagnostic(Integer testId, Integer score);
}
