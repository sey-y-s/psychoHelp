package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.DiagnosticResponseDTO;

import java.util.List;

public interface DiagnosticService {

    DiagnosticResponseDTO genererDiagnostic(Integer testId, Integer score);

    //Récupérer la liste des diagnostics liés à un test spécifique
    List<DiagnosticResponseDTO> obtenirDiagnosticsParTestId(Integer testId);
}
