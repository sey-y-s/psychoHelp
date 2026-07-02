package org.psychohelp.psychohelp.repository;

import org.psychohelp.psychohelp.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository  extends JpaRepository<Test, Integer> {


}
