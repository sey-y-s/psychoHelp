package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {
    List<Test> getAllTests();


    Optional<Test> getTestById(Integer id);


    Test saveTest(Test test);


    Test updateTest(int id, Test test);


    void deleteTest(int id);
}
