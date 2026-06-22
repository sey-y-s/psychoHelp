package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Conseil;

import java.util.List;

public interface ConseilService {

    public Conseil creer(Conseil utl);

    public List<Conseil> listeConseil();

    public Conseil conseilParId(Integer id);

    public Conseil modifier(int id, Conseil utl);

    public void supConseil(Integer id);

}
