package org.psychohelp.psychohelp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.NotificationResponseDTO;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.service.NotificationService;
import org.psychohelp.psychohelp.utils.Session;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications")
public class NotificationController {

    private final NotificationService ns;

    @GetMapping
    @Operation(summary = "Mes notifications")
    public List<NotificationResponseDTO> getNotifications(HttpSession session) {
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return ns.getNotifications(utilisateur.getId());
    }

    @GetMapping("/non-lues")
    @Operation(summary = "Mes notifications non lues")
    public List<NotificationResponseDTO> getNotificationsNonLues(HttpSession session) {
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return ns.getNotificationsNonLues(utilisateur.getId());
    }

    @GetMapping("/nombre-non-lues")
    @Operation(summary = "Nombre de notifications non lues")
    public long count(HttpSession session) {
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return ns.countNotificationsNonLues(utilisateur.getId());
    }

    @PutMapping("/{id}/lu")
    @Operation(summary = "Marquer une notification comme lue")
    public NotificationResponseDTO marquerCommeLue(@PathVariable int id, HttpSession session) {
        Utilisateur utilisateur = Session.getUtilisateur(session);
        return ns.marquerCommeLue(id, utilisateur.getId());
    }

    @PutMapping("/tout-lu")
    @Operation(summary = "Tout marquer comme lu")
    public void toutLire(HttpSession session) {
        Utilisateur utilisateur = Session.getUtilisateur(session);
        ns.marquerToutesCommeLues(utilisateur.getId());
    }
}
