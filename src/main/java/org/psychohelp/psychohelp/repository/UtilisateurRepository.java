package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
