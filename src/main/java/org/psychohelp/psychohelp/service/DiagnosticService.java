package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.DiagnosticResponseDTO;
public interface DiagnosticService {

    DiagnosticResponseDTO genererDiagnostic(Integer testId, Integer score);
}
