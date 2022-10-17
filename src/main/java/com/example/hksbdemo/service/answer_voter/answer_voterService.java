package com.example.hksbdemo.service.answer_voter;

import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.repository.answer_voterRepository;
import com.example.hksbdemo.domain.answer_voter.answer_voterSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class answer_voterService {

    private final answer_voterRepository answer_voterRepository;

    @Transactional
    public answer save(answer_voterSaveRequestDto requestDto) {return answer_voterRepository.save(requestDto.toEntity()).getAnswer();}
}
