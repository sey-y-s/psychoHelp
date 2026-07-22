package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Conseil;
import org.psychohelp.psychohelp.enumeration.StatusConseilEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConseilRepository extends JpaRepository<Conseil, Integer> {

   // List<Conseil> findByStatus(Boolean status);

    @Query("""
SELECT c FROM Conseil c JOIN FETCH c.psychologue """)
    List<Conseil> trouverTousAvecPsychologue();

    @Query(""" 
SELECT c FROM Conseil c JOIN FETCH c.psychologue WHERE c.status = :status
           """)
    List<Conseil> trouverParStatutAvecPsychologue(StatusConseilEnum status);
    //les conseils postés par un psychologue
    @Query("select c from Conseil c where c.psychologue.id=:psyschologueId ")
    List<Conseil>ConseilsByPyschologueId(@Param("psyschologueId") int psyschologueId);
}
