package com.example.hksbdemo.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private String subject;
    private SiteUser site_user;
    private String responseCode;

    public QuestionResponseDto(Question entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date();
        this.modify_date = entity.getModify_date();
        this.subject = entity.getSubject();
        this.site_user = entity.getSite_user();
    }

    public QuestionResponseDto() {
    }

    public QuestionResponseDto(Integer id, String content, LocalDateTime create_date, LocalDateTime modify_date, String subject, SiteUser site_user, String responseCode) {
        this.id = id;
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.subject = subject;
        this.site_user = site_user;
        this.responseCode = responseCode;
    }
}
