package com.example.hksbdemo.service;

import com.example.hksbdemo.domain.QuestionResponseDto;
import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.domain.QuestionSaveRequestDto;
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
        Optional <Question> oq = questionRepository.findById(id);
        Question q = oq.get();
        q.setContent(requestDto.getContent());
        q.setSubject(requestDto.getSubject());
        q.setModify_date(LocalDateTime.now());
        questionRepository.save(q);         // save 해주어야 함
        String result = "";
        boolean modifyQuestion = questionRepository.existsById(id);
        if(modifyQuestion == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void delete(Integer id, QuestionResponseDto responseDto) {
        questionRepository.deleteById(id);
        if(questionRepository.existsById(id) == false) {
            responseDto.setResponseCode("삭제성공");
        }
    }

    @Transactional
    public Object getDetail(Integer id) {
        Optional<Question> qd = questionRepository.findById(id);
        return qd.get();
    }
}
