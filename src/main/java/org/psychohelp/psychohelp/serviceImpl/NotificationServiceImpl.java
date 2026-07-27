package org.psychohelp.psychohelp.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.psychohelp.psychohelp.dto.NotificationResponseDTO;
import org.psychohelp.psychohelp.entity.Notification;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.TypeNotificationEnum;
import org.psychohelp.psychohelp.exceptions.UnauthorizedException;
import org.psychohelp.psychohelp.mapper.NotificationMapper;
import org.psychohelp.psychohelp.repository.NotificationRepository;
import org.psychohelp.psychohelp.service.NotificationService;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository nr;

    private final NotificationMapper nm;

    private final SimpMessagingTemplate messagingTemplate;


    @Override
    public void envoyer(Utilisateur destinataire, String titre, String message, TypeNotificationEnum type) {
        Notification notification = new Notification();

        notification.setUtilisateur(destinataire);
        notification.setTitre(titre);
        notification.setMessage(message);
        notification.setType(type);
        notification.setLu(false);

        Notification saved = nr.save(notification);

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + destinataire.getId(),
                nm.toDTO(saved)
        );
    }

    @Override
    public List<NotificationResponseDTO> getNotifications(int utilisateurId) {
        List<Notification> notifications =
                nr.findByUtilisateurId(
                        utilisateurId,
                        Sort.by(Sort.Direction.DESC, "dateCreation"));

        return nm.toDTO(notifications);
    }


    @Override
    public List<NotificationResponseDTO> getNotificationsNonLues(int utilisateurId) {

        List<Notification> notifications =
                nr.findByUtilisateurIdAndLuFalse(
                        utilisateurId,
                        Sort.by(Sort.Direction.DESC, "dateCreation")
                );
        return nm.toDTO(notifications);
    }

    @Override
    public long countNotificationsNonLues(int utilisateurId) {
        return nr.countByUtilisateurIdAndLuFalse(utilisateurId);
    }

    @Override
    public NotificationResponseDTO marquerCommeLue(int notificationId, int utilisateurId) {

        Notification notification = nr.findById(notificationId)
                .orElseThrow(() ->
                        new RuntimeException("Notification introuvable."));
        if(notification.getUtilisateur().getId() != utilisateurId) {
            throw new UnauthorizedException("Vous ne pouvez pas modifier cette notification.");
        }
        notification.setLu(true);
        Notification saved = nr.save(notification);

        return nm.toDTO(saved);
    }


    @Override
    public void marquerToutesCommeLues(int utilisateurId) {
        List<Notification> notifications =
                nr.findByUtilisateurIdAndLuFalse(
                        utilisateurId,
                        Sort.by(Sort.Direction.DESC, "dateCreation")
                );

        notifications.forEach(notification -> notification.setLu(true));

        nr.saveAll(notifications);

    }

}
