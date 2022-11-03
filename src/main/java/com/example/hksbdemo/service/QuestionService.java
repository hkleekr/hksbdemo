package com.example.hksbdemo.service;

import com.example.hksbdemo.DataNotFoundException;
import com.example.hksbdemo.domain.QuestionResponseDto;
import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.repository.QuestionRepository;
import com.example.hksbdemo.domain.QuestionSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
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

//    추천을 위해 추가
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

//    추천인 저장 10/26
//    Question Entity에 사용자를 추천인으로 바로 저장했음, 동작 확인하고 requestDto를 사용하도록 코드 수정할 것
    public void qVote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }

//     boardList를 위해 생성
    public Object getAll() {
        List<Question> oq = questionRepository.findAllByOrderByIdDesc();
        return oq;
    }
}
