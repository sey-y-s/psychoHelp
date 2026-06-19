package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.entity.Test;

import java.util.List;

public interface TestService {
    List<Test> getAllTests();


    Test getTestById(int id);


    Test saveTest(Test test);


    Test updateTest(int id, Test test);


    void deleteTest(int id);
}
