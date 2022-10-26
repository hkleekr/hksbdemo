package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.domain.question_voter.Question_Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Question_VoterRepository extends JpaRepository<Question_Voter, Long> {

}
