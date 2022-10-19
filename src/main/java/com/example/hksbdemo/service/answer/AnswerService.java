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
public class AnswerService {

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

    public void update(Integer id, AnswerSaveRequestDto requestDto, AnswerResponseDto responseDto) {
        Optional<answer> oa = answerRepository.findById(id);
        answer a = oa.get();
        a.setContent(requestDto.getContent());
        a.setModify_date(LocalDateTime.now());
//        return answerRepository.save(a).getId();
        String result = "";
        boolean modifyAnswer = answerRepository.existsById(id); // 수정시간이 다르면 성공** 체크해 볼 것
        if(modifyAnswer == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);

    }

    //    만들긴 했는데,, 맞나?
    public void delete(Integer id, AnswerResponseDto responseDto) {
        answerRepository.deleteById(id);
        if(answerRepository.existsById(id) == false) {
            responseDto.setResponseCode("삭제성공");
        }
    }

 /*   public Integer findAnswerId(Integer question_id, AnswerResponseDto responseDto) {
        answerRepository.findById(question_id);
        return answerRepository.getId();  // 답변의 id를 데려오고 싶은데...빨간색이네..
    }*/


}
