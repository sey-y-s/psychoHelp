package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Integer> {

    @Query("""
        SELECT d
        FROM Diagnostic d
        WHERE d.test.id = :testId
          AND :score BETWEEN d.scoreMin AND d.scoreMax
    """)
    Optional<Diagnostic> trouverDiagnostic(Integer testId, Integer score);
}
