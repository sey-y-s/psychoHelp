package org.psychohelp.psychohelp.controller;


import lombok.AllArgsConstructor;
import org.psychohelp.psychohelp.entity.Test;
import org.psychohelp.psychohelp.service.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
@AllArgsConstructor

public class TestController {


    private final TestService testService;


    @GetMapping
    public List<Test> getAllTests() {

        return testService.getAllTests();
    }


    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable int id) {

        return testService.getTestById(id);
    }


    @PostMapping
    public Test saveTest(@RequestBody Test test) {

        return testService.saveTest(test);
    }


    @PutMapping("/{id}")
    public Test updateTest(
            @PathVariable int id,
            @RequestBody Test test) {

        test.setId(id);

        return testService.saveTest(test);
    }


    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable int id) {

        testService.getTestById(id);

    }

}