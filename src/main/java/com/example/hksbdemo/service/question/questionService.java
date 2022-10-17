package com.example.hksbdemo.service.question;

import com.example.hksbdemo.domain.answer.answerSaveRequestDto;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.domain.question.questionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class questionService {

    private final questionRepository questionRepository;


    public Integer save(questionSaveRequestDto requestDto) {
        return questionRepository.save(requestDto.toEntity()).getId();
    }


    public Integer update(Integer id, questionSaveRequestDto requestDto) {
        Optional <question> oq = questionRepository.findById(id);
        question q = oq.get();
        q.setContent(requestDto.getContent());
        q.setSubject(requestDto.getSubject());
        q.setModify_date(LocalDateTime.now());
        return questionRepository.save(q).getId();
    }

//    만들긴 했는데,, 맞나?
    public Integer delete(Integer id, questionSaveRequestDto requestDto) {
        questionRepository.deleteById(id);
        return questionRepository.save(requestDto.toEntity()).getId();
    }

    public Object getDetail(Integer id) {
        Optional<question> qd = questionRepository.findById(id);
        return qd.get();
    }
}
