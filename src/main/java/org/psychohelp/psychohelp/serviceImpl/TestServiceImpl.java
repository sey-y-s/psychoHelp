package org.psychohelp.psychohelp.serviceImpl;

import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.dto.CategorieReponseDTO;
import org.psychohelp.psychohelp.dto.TestReponseDTO;
import org.psychohelp.psychohelp.dto.TestRequestDTO;
import org.psychohelp.psychohelp.entity.CategorieTest;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.repository.CategorieRepository;
import org.psychohelp.psychohelp.repository.TestRepository;
import org.psychohelp.psychohelp.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private  final CategorieRepository categorieRepository;

    @Override
    public List<TestReponseDTO> getAllTests() {
       // return List.of();
        return testRepository.findAll().stream().map(
                tests -> new TestReponseDTO(tests.getId(), tests.getNom_test(), tests.getCategorieTest().getNomCategorie())
        ).toList();
    }

    @Override
    public Optional<TestReponseDTO> getTestById(Integer id) {

        return testRepository.findById(id).map(
                test -> new TestReponseDTO(test.getId(), test.getNom_test(), test.getCategorieTest().getNomCategorie())
        );
    }

    @Override
    public TestReponseDTO saveTest(TestRequestDTO testDTO, Integer categories_test_id) {
        CategorieTest categorie = new CategorieTest();
        categorie.setId(categories_test_id);
        CategorieTest cat = categorieRepository.findById(categories_test_id)
                .orElseThrow(()-> new  RuntimeException("categorie test non trouvé"));

        Test test = new Test() ;
        test.setNom_test(testDTO.getNom_test());
        test.setDescription(testDTO.getDescription());
        test.setEtat(testDTO.getEtat());
        test.setCategorieTest(categorie);


        testRepository.save(test);
        return new TestReponseDTO(test.getId(), test.getNom_test(), cat.getNomCategorie());
    }

    @Override
    public TestReponseDTO updateTest(int id, TestRequestDTO testDTO) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test non trouvé"));
        test.setNom_test(testDTO.getNom_test());
        test.setDescription(testDTO.getDescription());
        test.setEtat(testDTO.getEtat());
        testRepository.save(test);
        return new TestReponseDTO(test.getId(), test.getNom_test(), test.getCategorieTest().getNomCategorie());
    }

    @Override
    public void deleteTest(int id) {
        testRepository.deleteById(id);

    }
}
