package com.example.hksbdemo.service;

import com.example.hksbdemo.service.SiteUserService;
import com.example.hksbdemo.domain.QuestionResponseDto;
import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.repository.questionRepository;
import com.example.hksbdemo.domain.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final questionRepository questionRepository;
    private SiteUserService siteUserService;

//    질문 등록, 작성자 연결
    @Transactional
    public void save(QuestionSaveRequestDto requestDto, QuestionResponseDto responseDto, SiteUser account ) {
        requestDto.setSite_user(account);
        Integer getId = questionRepository.save(requestDto.toEntity()).getId();
        String result = "";
        boolean question = questionRepository.existsById(getId);
        if(question == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

//    질문 수정
    @Transactional
    public void update(Integer id, QuestionSaveRequestDto requestDto, QuestionResponseDto responseDto) {
        Optional <Question> oq = questionRepository.findById(id);
        Question q = oq.get();
        q.setContent(requestDto.getContent());
        q.setSubject(requestDto.getSubject());
        q.setModify_date(LocalDateTime.now());
        questionRepository.save(q);         // set으로 바꿔준 다음, save 해야 함
        String result = "";
        boolean modifyQuestion = questionRepository.existsById(id);
        if(modifyQuestion == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

//    질문 삭제
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
