package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.ResultatTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResultatTestRepository extends JpaRepository<ResultatTest, Long> {
    // Pour afficher l'historique des tests d'un citoyen
    List<ResultatTest> findByCitoyenId(Long citoyenId);
}