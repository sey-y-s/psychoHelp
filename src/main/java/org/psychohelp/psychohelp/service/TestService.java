package org.psychohelp.psychohelp.service;

import org.psychohelp.psychohelp.dto.TestReponseDTO;
import org.psychohelp.psychohelp.dto.TestRequestDTO;
import org.psychohelp.psychohelp.entity.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {
    List<TestReponseDTO> getAllTests();


    Optional<TestReponseDTO> getTestById(Integer id);


    TestReponseDTO saveTest(TestRequestDTO testDTO, Integer categories_test_id);


    TestReponseDTO updateTest(int id, TestRequestDTO testDTO);


    void deleteTest(int id);
}
