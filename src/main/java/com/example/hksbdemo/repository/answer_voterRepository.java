package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.answer_voter.answer_voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface answer_voterRepository extends JpaRepository <answer_voter, Long> {
}

//Long 맞나 확인 필요함