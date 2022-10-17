package com.example.hksbdemo.domain.answer;

import com.example.hksbdemo.domain.answer.answer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class answerResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private com.example.hksbdemo.domain.site_user.site_user site_user;
    private com.example.hksbdemo.domain.question.question question;

    public answerResponseDto(answer entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date();
        this.modify_date = entity.getModify_date();
        this.site_user = entity.getSite_user();
        this.question = entity.getQuestion();
    }
}

//마지막 두줄 확인필요
