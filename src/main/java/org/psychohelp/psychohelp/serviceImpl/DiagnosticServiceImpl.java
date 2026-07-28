package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.DiagnosticResponseDTO;
import org.psychohelp.psychohelp.entity.Diagnostic;
import org.psychohelp.psychohelp.repository.DiagnosticRepository;
import org.psychohelp.psychohelp.service.DiagnosticService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DiagnosticServiceImpl implements DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;


    @Override
    public DiagnosticResponseDTO genererDiagnostic(Integer testId, Integer score) {
        Diagnostic diagnostic = diagnosticRepository.trouverDiagnostic(testId, score)
                        .orElseThrow(() ->
                                new RuntimeException("Diagnostic introuvable"));
        return new DiagnosticResponseDTO(score, diagnostic.getNiveau(), diagnostic.getMessage(),diagnostic.getScoreMax(),diagnostic.getScoreMin(),diagnostic.getTest().getId());
    }

    // 👈 AJOUT : Implémentation de la récupération par Test ID
    @Override
    public List<DiagnosticResponseDTO> obtenirDiagnosticsParTestId(Integer testId) {
        //Récupération des entités depuis la table MySQL
        List<Diagnostic> diagnostics = diagnosticRepository.findByTestId(testId);

        //Conversion de la liste d'entités en liste de DTOs via un flux Stream
        return diagnostics.stream().map(d -> {
            DiagnosticResponseDTO dto = new DiagnosticResponseDTO();
            dto.setId(d.getId());
            dto.setMessage(d.getMessage());
            dto.setNiveau(d.getNiveau());
            dto.setScoreMax(d.getScoreMax());
            dto.setScoreMin(d.getScoreMin());
            dto.setTestId(testId);
            return dto;
        }).collect(Collectors.toList());
    }
}

