package org.psychohelp.psychohelp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.DiagnosticResponseDTO;
import org.psychohelp.psychohelp.entity.Diagnostic;
import org.psychohelp.psychohelp.repository.DiagnosticRepository;
import org.psychohelp.psychohelp.service.DiagnosticService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiagnosticServiceImpl implements DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;

    @Override
    public DiagnosticResponseDTO genererDiagnostic(Integer testId, Integer score) {
        Diagnostic diagnostic = diagnosticRepository.trouverDiagnostic(testId, score)
                        .orElseThrow(() ->
                                new RuntimeException("Diagnostic introuvable"));
        return new DiagnosticResponseDTO(score, diagnostic.getNiveau(), diagnostic.getMessage());
    }
}

