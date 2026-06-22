package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitoyenRepository extends JpaRepository <Citoyen, Integer> {
}
