package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreneauRepository extends JpaRepository<Creneau, Long> {

    List<Creneau> findByStatutFalse();

    List<Creneau> findByPsychologueIdAndStatutFalse(int psychologue_id);
}
