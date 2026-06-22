package org.psychohelp.psychohelp.serviceImpl;

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

import java.util.List;

public class ResultatServiceImpl implements ResultatService {
    private final ResultatTestRepository resultatTestRepository;
    private final CitoyenRepository citoyenRepository;
    private final TestRepository testRepository;
    private final ChoixMultipleRepository choixMutipleRepository;
    private final DiagnosticService diagnosticService;

    public ResultatServiceImpl(CitoyenRepository citoyenRepository, TestRepository testRepository,
                                   ChoixMultipleRepository choixMutipleRepository,
                                   ResultatTestRepository resultatTestRepository,
                                   DiagnosticService diagnosticService
    ){
        this.resultatTestRepository = resultatTestRepository;
        this.citoyenRepository = citoyenRepository;
        this.testRepository = testRepository;
        this.choixMutipleRepository = choixMutipleRepository;
        this.diagnosticService = diagnosticService;
    }




    @Override
    public ResultatTest calculerEtEnregistrerResultat(Long citoyenId, Integer testId, List<Integer> choixIds) {
        Citoyen citoyen = citoyenRepository.findById(citoyenId).orElseThrow(
                () -> new RuntimeException("Citoyen introuvable")
        );

        Test test = testRepository.findById(testId).orElseThrow(
                () -> new RuntimeException("Test introuvable")
        );

        List<ChoixMultiple> choixSelectionnes = choixMutipleRepository.findAllById(choixIds);
        int scoreTotal = 0;
        for (ChoixMultiple cm : choixSelectionnes) {
            scoreTotal += cm.getScore();
        }
        String diagnostic = diagnosticService.genererDiagnosticScientifique(test.getNom_test(), scoreTotal);

        ResultatTest resultatTest = new ResultatTest();
        resultatTest.setCitoyen(citoyen);
        resultatTest.setTest(test);
        resultatTest.setScore(scoreTotal);
        resultatTest.setDescription(diagnostic);


        return resultatTestRepository.save(resultatTest);
    }

    @Override
    public List<ResultatTest> obtenirResultatsParCitoyen(Long citoyenId) {
        return resultatTestRepository.findByCitoyenId(citoyenId);
    }
}
