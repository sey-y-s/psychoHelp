package org.psychohelp.psychohelp.controller;


import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.repository.TestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@AllArgsConstructor

public class TestController {


    private final TestRepository testRepository;


    @GetMapping
    public List<Test> getAllTests() {

        return testRepository.findAll();
    }


    @GetMapping("/{id}")
    public Test getTestById(@PathVariable int id) {

        return testRepository.findById(id)
                .orElse(null);
    }


    @PostMapping
    public Test saveTest(@RequestBody Test test) {

        return testRepository.save(test);
    }


    @PutMapping("/{id}")
    public Test updateTest(
            @PathVariable int id,
            @RequestBody Test test) {

        test.setId(id);

        return testRepository.save(test);
    }


    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable int id) {

        testRepository.deleteById(id);

    }

}