package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.answer.answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface answerRepository extends JpaRepository<answer, Integer> {
}

//Long 맞나 확인 필요함