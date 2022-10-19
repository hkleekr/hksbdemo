package com.example.hksbdemo.service.question;

import com.example.hksbdemo.domain.question.QuestionResponseDto;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.domain.question.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final questionRepository questionRepository;

    @Transactional
    public void save(QuestionSaveRequestDto requestDto, QuestionResponseDto responseDto) {
        Integer getId = questionRepository.save(requestDto.toEntity()).getId();
        String result = "";
        boolean question = questionRepository.existsById(getId);
        if(question == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void update(Integer id, QuestionSaveRequestDto requestDto, QuestionResponseDto responseDto) {
        Optional <question> oq = questionRepository.findById(id);
        question q = oq.get();
        q.setContent(requestDto.getContent());
        q.setSubject(requestDto.getSubject());
        String result = "";
        boolean modifyQuestion = questionRepository.existsById(id);
        if(modifyQuestion == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }


    public void delete(Integer id, QuestionResponseDto responseDto) {
        questionRepository.deleteById(id);
        if(questionRepository.existsById(id) == false) {
            responseDto.setResponseCode("삭제성공");
        }
    }

    public Object getDetail(Integer id) {
        Optional<question> qd = questionRepository.findById(id);
        return qd.get();
    }
}
