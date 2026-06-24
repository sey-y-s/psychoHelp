package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Creneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreneauRepository extends JpaRepository<Creneau, Long> {

    List<Creneau> findByStatutTrue();

    List<Creneau> findByPsychologueIdAndStatutTrue(int psychologue_id);
}
