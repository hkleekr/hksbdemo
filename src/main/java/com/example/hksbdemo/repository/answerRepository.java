package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface answerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findById(String subject);

}

