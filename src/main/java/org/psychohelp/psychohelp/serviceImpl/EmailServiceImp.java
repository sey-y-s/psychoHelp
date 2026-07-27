package org.psychohelp.psychohelp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void envoyerEmailTest(String destination) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(destination);
        message.setSubject("Test pyschoHelp");

        message.setText("""
                Bonjour,
                Ceci est un email de test envoyé depuis l'API pyschoHelp.
                Si vous recevez ce message, cela signifie la configuration SMTP fonctionne correcte.
                Cordialement,
                L'equipe pyschoHelp.
                """);
        mailSender.send(message);

    }

    @Override
    public void envoyerCompteActif(String mail, String nom, String prenom) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("psychohelp@gmail.com");
        message.setTo(mail);
        message.setSubject("Activation de votre compte PsychoHelp.");
        message.setText(
                "Bonjour " + nom + " " + prenom + ", \n\n"
                + "Votre compte PsychoHelp a été activé avec succes.\n"
                + "Vous pouvez maintenant vous connecter a la plateforme.\n\n"
                + "Cordialement,\n"
                + "L'equipe pyschoHelp.\n"
        );

        mailSender.send(message);
    }
}
