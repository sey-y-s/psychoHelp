package org.psychohelp.psychohelp.controller;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.DiagnosticResponseDTO;
import org.psychohelp.psychohelp.service.DiagnosticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/diagnostics")
public class DiagnosticControlleur {

        private final DiagnosticService diagnosticService;

        //C'est cet URL exacte que votre test.service.ts appellera en GET !
        @GetMapping("/test/{testId}")
        public ResponseEntity<List<DiagnosticResponseDTO>> obtenirDiagnosticsParTestId(@PathVariable Integer testId) {
            List<DiagnosticResponseDTO> dtos = diagnosticService.obtenirDiagnosticsParTestId(testId);
            return ResponseEntity.ok(dtos);
        }

}
