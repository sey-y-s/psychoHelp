package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.ResultatTestRequestDTO;
import org.psychohelp.psychohelp.dto.ResultatTestResponseDTO;
import java.util.List;

public interface ResultatService {
    ResultatTestResponseDTO calculerEtEnregistrerResultat(ResultatTestRequestDTO requestDTO);
    List<ResultatTestResponseDTO> obtenirResultatsParCitoyen(Integer citoyenId);
}