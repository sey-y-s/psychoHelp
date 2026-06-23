package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.repository.QuestionsTestRepository;
import org.psychohelp.psychohelp.repository.TestRepository;
import org.psychohelp.psychohelp.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public List<Test> getAllTests() {
       // return List.of();
        return testRepository.findAll();
    }

    @Override
    public Optional<Test> getTestById(Integer id) {
        return testRepository.findById(id);
    }

    @Override
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test updateTest(int id, Test test) {
        return testRepository.save(test);

    }

    @Override
    public void deleteTest(int id) {
        testRepository.deleteById(id);

    }
}
