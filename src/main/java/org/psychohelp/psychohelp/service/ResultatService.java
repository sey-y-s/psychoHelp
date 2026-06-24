package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.ResultatTest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ResultatService {
    ResultatTest calculerEtEnregistrerResultat( Integer citoyenId, Integer testId, List<Integer> choixIds);

    List<ResultatTest> obtenirResultatsParCitoyen(Integer citoyenId);
}
