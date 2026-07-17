package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.NotificationResponseDTO;
import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Notification;
import org.psychohelp.psychohelp.entity.Seance;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.TypeNotificationEnum;

import java.util.List;

public interface NotificationService {

        void envoyer(
                Utilisateur destinataire,
                String titre,
                String message,
                TypeNotificationEnum type
        );

        List<NotificationResponseDTO> getNotifications(int utilisateurId);

        List<NotificationResponseDTO> getNotificationsNonLues(int utilisateurId);

        long countNotificationsNonLues(int utilisateurId);

        NotificationResponseDTO marquerCommeLue(int notificationId, int utilisateurId);

        void marquerToutesCommeLues(int utilisateurId);

}
