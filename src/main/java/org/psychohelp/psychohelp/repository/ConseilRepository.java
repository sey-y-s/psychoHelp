package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConseilRepository extends JpaRepository<Conseil, Integer> {

    List<Conseil> findByStatus(Boolean status);
}
