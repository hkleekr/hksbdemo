package com.example.hksbdemo.domain.answer;

import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.domain.site_user.site_user;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private com.example.hksbdemo.domain.site_user.site_user site_user;
    private com.example.hksbdemo.domain.question.question question;

    private String responseCode;

    public AnswerResponseDto(answer entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date();
        this.modify_date = entity.getModify_date();
        this.site_user = entity.getSite_user();
        this.question = entity.getQuestion();
    }

    public AnswerResponseDto(Integer id, String content, LocalDateTime create_date, LocalDateTime modify_date, com.example.hksbdemo.domain.site_user.site_user site_user, com.example.hksbdemo.domain.question.question question, String responseCode) {
        this.id = id;
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.site_user = site_user;
        this.question = question;
        this.responseCode = responseCode;
    }

    public AnswerResponseDto() {

    }
}

//마지막 두줄 확인필요
