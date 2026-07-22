package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
     List<Notification> findByUtilisateurId(int utilisateurId, Sort sort);
     List<Notification> findByUtilisateurIdAndLuFalse(int utilisateurId, Sort sort);
     long countByUtilisateurIdAndLuFalse(int utilisateurId);
}
