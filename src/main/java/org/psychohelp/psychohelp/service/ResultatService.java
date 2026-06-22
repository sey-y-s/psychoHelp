package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.ResultatTest;

import java.util.List;

public interface ResultatService {
    ResultatTest calculerEtEnregistrerResultat(Long citoyenId, Long testId, List<Long> choixIds);

    List<ResultatTest> obtenirResultatsParCitoyen(Long citoyenId);
}
