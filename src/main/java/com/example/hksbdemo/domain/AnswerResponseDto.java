package com.example.hksbdemo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private SiteUser site_user;
    private Question question;

    private String responseCode;

    public AnswerResponseDto(Answer entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date();
        this.modify_date = entity.getModify_date();
        this.site_user = entity.getSite_user();
        this.question = entity.getQuestion();
    }

    public AnswerResponseDto(Integer id, String content, LocalDateTime create_date, LocalDateTime modify_date, SiteUser site_user, Question question, String responseCode) {
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
