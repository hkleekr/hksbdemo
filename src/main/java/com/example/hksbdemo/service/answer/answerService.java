package com.example.hksbdemo.service.answer;

import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.domain.question.questionSaveRequestDto;
import com.example.hksbdemo.repository.answerRepository;
import com.example.hksbdemo.domain.answer.answerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class answerService {

    private final answerRepository answerRepository;

    @Transactional
    public Integer save(answerSaveRequestDto requestDto) {
        return answerRepository.save(requestDto.toEntity()).getId();}

    public Integer update(Integer id, answerSaveRequestDto requestDto) {
        Optional<answer> oa = answerRepository.findById(id);
        answer a = oa.get();
        a.setContent(requestDto.getContent());
        a.setModify_date(LocalDateTime.now());
        return answerRepository.save(a).getId();
    }

    //    만들긴 했는데,, 맞나?
    public Integer delete(Integer id, answerSaveRequestDto requestDto) {
        answerRepository.deleteById(id);
        return answerRepository.save(requestDto.toEntity()).getId();
    }


}
