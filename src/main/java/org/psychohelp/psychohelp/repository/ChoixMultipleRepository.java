package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.ChoixMultiple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoixMultipleRepository extends JpaRepository<ChoixMultiple, Integer> {
    // ← AJOUT
    List<ChoixMultiple> findByQuestionTestId(Integer questionId);
}