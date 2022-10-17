package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.question_voter.question_voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface question_voterRepository extends JpaRepository<question_voter, Long> {
}

//Long 맞나 확인 필요함