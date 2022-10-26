package com.example.hksbdemo.service;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.domain.question_voter.Question_Voter;
import com.example.hksbdemo.repository.QuestionRepository;
import com.example.hksbdemo.repository.Question_VoterRepository;
import com.example.hksbdemo.domain.question_voter.question_voterSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class Question_VoterService {

    private final Question_VoterRepository question_voterRepository;
    private final QuestionRepository questionRepository;

    public Question save(question_voterSaveRequestDto requestDto) { return question_voterRepository.save(requestDto.toEntity()).getQuestion();}

}
