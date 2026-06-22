package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Psychologue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychologueRepository extends JpaRepository<Psychologue,Integer> {
}
