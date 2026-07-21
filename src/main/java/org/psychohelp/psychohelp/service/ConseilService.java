package org.psychohelp.psychohelp.service;

import jakarta.servlet.http.HttpSession;
import org.psychohelp.psychohelp.dto.ConseilAfficheDto;
import org.psychohelp.psychohelp.dto.ConseilDtoForPyschologue;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;

import java.util.List;

public interface ConseilService {

    public Conseil creer(Conseil utl);

    List<ConseilAfficheDto> listeConseil();

    public Conseil conseilParId(Integer id);

    public Conseil modifier(int id, Conseil utl);

    public void supConseil(Integer id);

    public List<ConseilAfficheDto> listConseilParStatus(Boolean status);
    List<ConseilDtoForPyschologue> ConseilsByPyschologueId( HttpSession session);


}
