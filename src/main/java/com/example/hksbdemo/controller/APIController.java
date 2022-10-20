package com.example.hksbdemo.controller;

import java.util.List;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.repository.questionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class APIController {

    @Autowired
    private questionRepository repository;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/questions")
    List<Question> all(@RequestParam(required = false, defaultValue = "") String subject,
                       @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(subject) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findBySubjectOrContent(subject, content);
        }
    }
    // end::get-aggregate-root[]

    @PostMapping("/questions")
    Question newquestion(@RequestBody Question newquestion) {
        return repository.save(newquestion);
    }

    // Single item

    @GetMapping("/questions/{id}")
    Question one(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/questions/{id}")
    Question replacequestion(@RequestBody Question newquestion, @PathVariable Integer id) {

        return repository.findById(id)
                .map(question -> {
                    question.setSubject(newquestion.getSubject());
                    question.setContent(newquestion.getContent());
                    return repository.save(question);
                })
                .orElseGet(() -> {
                    newquestion.setId(id);
                    return repository.save(newquestion);
                });
    }

    @DeleteMapping("/questions/{id}")
    void deletequestion(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}