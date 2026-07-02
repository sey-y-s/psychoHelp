package org.psychohelp.psychohelp.serviceImpl;

import jakarta.transaction.Transactional;
import org.psychohelp.psychohelp.dto.ResultatTestMapper;
import org.psychohelp.psychohelp.dto.ResultatTestRequestDTO;
import org.psychohelp.psychohelp.dto.ResultatTestResponseDTO;
import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.psychohelp.psychohelp.entity.Citoyen;
import org.psychohelp.psychohelp.entity.ResultatTest;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.repository.ChoixMultipleRepository;
import org.psychohelp.psychohelp.repository.CitoyenRepository;
import org.psychohelp.psychohelp.repository.ResultatTestRepository;
import org.psychohelp.psychohelp.repository.TestRepository;
import org.psychohelp.psychohelp.service.DiagnosticService;
import org.psychohelp.psychohelp.service.ResultatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ResultatServiceImpl implements ResultatService {

    private final ResultatTestRepository resultatTestRepository;
    private final CitoyenRepository citoyenRepository;
    private final TestRepository testRepository;
    private final ChoixMultipleRepository choixMultipleRepository; // Correction de la faute de frappe "choixMutipleRepository"
    private final DiagnosticService diagnosticService;

    public ResultatServiceImpl(CitoyenRepository citoyenRepository,
                               TestRepository testRepository,
                               ChoixMultipleRepository choixMultipleRepository,
                               ResultatTestRepository resultatTestRepository,
                               DiagnosticService diagnosticService) {
        this.resultatTestRepository = resultatTestRepository;
        this.citoyenRepository = citoyenRepository;
        this.testRepository = testRepository;
        this.choixMultipleRepository = choixMultipleRepository;
        this.diagnosticService = diagnosticService;
    }

    @Override
    public ResultatTestResponseDTO calculerEtEnregistrerResultat(ResultatTestRequestDTO requestDTO) {

        Citoyen citoyen = citoyenRepository.findById(Math.toIntExact(requestDTO.getCitoyenId())).orElseThrow(
                () -> new RuntimeException("Citoyen introuvable avec l'ID : " + requestDTO.getCitoyenId())
        );

        Test test = testRepository.findById(requestDTO.getTestId()).orElseThrow(
                () -> new RuntimeException("Test introuvable avec l'ID : " + requestDTO.getTestId())
        );


        List<ChoixMultiple> choixSelectionnes = choixMultipleRepository.findAllById(requestDTO.getChoixIds());


        int scoreTotal = 0;
        for (ChoixMultiple cm : choixSelectionnes) {
            scoreTotal += cm.getScore();
        }


        String diagnostic = diagnosticService.genererDiagnosticScientifique(test.getNom_test(), scoreTotal);

        // 6. Construction de l'entité ResultatTest
        ResultatTest resultatTest = new ResultatTest();
        resultatTest.setCitoyen(citoyen);
        resultatTest.setTest(test);
        resultatTest.setScore(scoreTotal);
        resultatTest.setDescription(diagnostic);


        ResultatTest resultatSauvegarde = resultatTestRepository.save(resultatTest);


        return ResultatTestMapper.toDTO(resultatSauvegarde);
    }

    @Override
    public List<ResultatTestResponseDTO> obtenirResultatsParCitoyen(Integer citoyenId) {
        // Récupération de la liste des entités
        List<ResultatTest> resultats = resultatTestRepository.findByCitoyenId(citoyenId);

        // Conversion de la liste d'entités en liste de DTOs
        return resultats.stream()
                .map(ResultatTestMapper::toDTO)
                .toList();
    }
}