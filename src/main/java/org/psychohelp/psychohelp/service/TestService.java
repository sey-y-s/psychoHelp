package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.TestDTO;
import org.psychohelp.psychohelp.entity.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {
    List<Test> getAllTests();


    Optional<Test> getTestById(Integer id);


    Test saveTest(TestDTO testDTO);


    Test updateTest(int id, TestDTO testDTO);


    void deleteTest(int id);
}
