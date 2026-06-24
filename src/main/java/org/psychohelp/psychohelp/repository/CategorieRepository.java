package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.CategorieTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository
        extends JpaRepository<CategorieTest, Integer> {

}