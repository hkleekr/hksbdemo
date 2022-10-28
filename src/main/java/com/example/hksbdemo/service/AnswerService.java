package com.example.hksbdemo.service;

import com.example.hksbdemo.DataNotFoundException;
import com.example.hksbdemo.domain.*;
import com.example.hksbdemo.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.domain.Answer;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

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

//    추천을 위해 추가
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("Answer not found");
        }
    }

//    추천인 저장 10/26
//    Answer Entity에 사용자를 추천인으로 바로 저장했음, 동작 확인하고 requestDto를 사용하도록 코드 수정할 것
    public void aVote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
