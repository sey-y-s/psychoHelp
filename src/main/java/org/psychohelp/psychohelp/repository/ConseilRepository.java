package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConseilRepository extends JpaRepository<Conseil, Integer> {

   // List<Conseil> findByStatus(Boolean status);

    @Query("""
SELECT c FROM Conseil c JOIN FETCH c.psychologue """)
    List<Conseil> trouverTousAvecPsychologue();

    @Query(""" 
SELECT c FROM Conseil c JOIN FETCH c.psychologue WHERE c.status = :status
           """)
    List<Conseil> trouverParStatutAvecPsychologue(Boolean status);
}
