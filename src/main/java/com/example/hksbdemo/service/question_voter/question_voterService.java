package com.example.hksbdemo.service.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.repository.question_voterRepository;
import com.example.hksbdemo.domain.question_voter.question_voterSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class question_voterService {

    private final question_voterRepository question_voterRepository;

    @Transactional
    public Question save(question_voterSaveRequestDto requestDto) { return question_voterRepository.save(requestDto.toEntity()).getQuestion();}
}
