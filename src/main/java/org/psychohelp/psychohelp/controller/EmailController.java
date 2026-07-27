package org.psychohelp.psychohelp.controller;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/test")
    public String envoyerTest(@RequestParam String destinataire) {
        emailService.envoyerEmailTest(destinataire);
        return "Email envoyé avec succés";
    }
}
