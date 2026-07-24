package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.dto.CategorieReponseDTO;
import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository
        extends JpaRepository<CategorieTest, Integer> {

    @Query("""
    SELECT new org.psychohelp.psychohelp.dto.CategorieReponseDTO(c.id, c.nomCategorie, c.description)
    FROM Test t
    JOIN CategorieTest c ON t.categorieTest.id = c.id
    WHERE t.id = :testId
""")
    CategorieReponseDTO getCategorieByTestId(@Param("testId") int testId);
}