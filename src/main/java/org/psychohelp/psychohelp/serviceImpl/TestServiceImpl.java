package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {
    @Override
    public List<Test> getAllTests() {
        return List.of();
    }

    @Override
    public Test getTestById(int id) {
        return null;
    }

    @Override
    public Test saveTest(Test test) {
        return null;
    }

    @Override
    public Test updateTest(int id, Test test) {
        return null;
    }

    @Override
    public void deleteTest(int id) {

    }
}
