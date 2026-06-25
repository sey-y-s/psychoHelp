package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.TestDTO;
import org.psychohelp.psychohelp.entity.Test;
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
    public Test saveTest(TestDTO testDTO) {

        Test test = new Test() ;
        test.setNom_test(testDTO.getNom_test());
        test.setDescription(testDTO.getDescription());
        test.setEtat(testDTO.getEtat());

        return testRepository.save(test);
    }

    @Override
    public Test updateTest(int id, TestDTO testDTO) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test non trouvé"));
        test.setNom_test(testDTO.getNom_test());
        test.setDescription(testDTO.getDescription());
        test.setEtat(testDTO.getEtat());
        return testRepository.save(test);

    }

    @Override
    public void deleteTest(int id) {
        testRepository.deleteById(id);

    }
}
