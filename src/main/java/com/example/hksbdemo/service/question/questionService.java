package com.example.hksbdemo.service.question;

import com.example.hksbdemo.domain.question.QuestionResponseDto;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.domain.question.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class questionService {

    private final questionRepository questionRepository;


    public Integer save(QuestionSaveRequestDto requestDto) {
        return questionRepository.save(requestDto.toEntity()).getId();
    }


    public Integer update(Integer id, QuestionSaveRequestDto requestDto) {
        Optional <question> oq = questionRepository.findById(id);
        question q = oq.get();
        q.setContent(requestDto.getContent());
        q.setSubject(requestDto.getSubject());
        q.setModify_date(LocalDateTime.now());
        return questionRepository.save(q).getId();
    }

//    만들긴 했는데,, 맞나?
    public void delete(Integer id, QuestionResponseDto responseDto) {
        questionRepository.deleteById(id);
        if(questionRepository.existsById(id) == false) {
            responseDto.setResponseCode("삭제성공");
        }
//        return questionRepository.save(requestDto.toEntity()).getId(); // delete인데 .save(getId())는 이상한 일..
    }

    public Object getDetail(Integer id) {
        Optional<question> qd = questionRepository.findById(id);
        return qd.get();
    }
}
