package org.psychohelp.psychohelp.service;

public interface EmailService {
    void envoyerEmailTest(String destinataire);

    void envoyerCompteActif(String mail, String nom, String prenom);
}
