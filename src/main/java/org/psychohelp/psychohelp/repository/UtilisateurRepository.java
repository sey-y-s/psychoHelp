package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Utilisateur;
import org.psychohelp.psychohelp.enumeration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByMail(String mail);
    Optional<Utilisateur> findByRole(RoleEnum role);
    List<Utilisateur> findTop5ByOrderByDateCreationDesc();

}

