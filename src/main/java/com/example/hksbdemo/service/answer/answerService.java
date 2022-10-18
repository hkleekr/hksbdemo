package com.example.hksbdemo.service.answer;

import com.example.hksbdemo.domain.answer.AnswerResponseDto;
import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.repository.answerRepository;
import com.example.hksbdemo.domain.answer.AnswerSaveRequestDto;
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
    public void save(AnswerSaveRequestDto requestDto, AnswerResponseDto responseDTO) {
       Integer getId = answerRepository.save(requestDto.toEntity()).getId();
       String result = "";
        boolean answer = answerRepository.existsById(getId);
        if(answer == true){
            result= "성공";
        }
        responseDTO.setResponseCode(result);
        }

    public Integer update(Integer id, AnswerSaveRequestDto requestDto) {
        Optional<answer> oa = answerRepository.findById(id);
        answer a = oa.get();
        a.setContent(requestDto.getContent());
        a.setModify_date(LocalDateTime.now());
        return answerRepository.save(a).getId();
    }

    //    만들긴 했는데,, 맞나?
    public Integer delete(Integer id, AnswerSaveRequestDto requestDto) {
        answerRepository.deleteById(id);
        return answerRepository.save(requestDto.toEntity()).getId();
    }


}
