package com.example.hksbdemo.service;

import com.example.hksbdemo.domain.*;
import com.example.hksbdemo.repository.answerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final answerRepository answerRepository;

//    답변 등록, 작성자 연결
    @Transactional
    public void save(AnswerSaveRequestDto requestDto, AnswerResponseDto responseDTO, SiteUser account) {
        requestDto.setSite_user(account);
        Integer getId = answerRepository.save(requestDto.toEntity()).getId();
        String result = "";
        boolean answer = answerRepository.existsById(getId);
        if(answer == true){
            result= "성공";
        }
        responseDTO.setResponseCode(result);
        }

//    답변 수정
    @Transactional
    public void update(Integer id, AnswerSaveRequestDto requestDto, AnswerResponseDto responseDto) {
        Optional<Answer> oa = answerRepository.findById(id);
        Answer a = oa.get();
        a.setContent(requestDto.getContent());
        a.setModify_date(LocalDateTime.now());
        answerRepository.save(a);         // save 해주어야 함
        String result = "";
        boolean modifyAnswer = answerRepository.existsById(id);
        if(modifyAnswer == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

//    답변 삭제
    @Transactional
    public void delete(Integer id, AnswerResponseDto responseDto) {
        answerRepository.deleteById(id);
        if(answerRepository.existsById(id) == false) {
            responseDto.setResponseCode("삭제성공");
        }
    }
}
