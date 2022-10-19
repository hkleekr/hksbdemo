package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.domain.question.question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface answerRepository extends JpaRepository<answer, Integer> {
    List<answer> findById(String subject);

}

//Long 맞나 확인 필요함