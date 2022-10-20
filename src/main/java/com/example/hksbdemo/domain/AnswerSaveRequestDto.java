package com.example.hksbdemo.domain;

import com.example.hksbdemo.domain.site_user.SiteUser;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerSaveRequestDto {

    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private SiteUser site_user;
    private Question question;

    @Builder
    public AnswerSaveRequestDto(String content, LocalDateTime create_date, LocalDateTime modify_date, SiteUser site_user, Question question) {
        this.id = id;
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.site_user = site_user;
        this.question = question;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .create_date(create_date)
                .modify_date(modify_date)
                .site_user(site_user)
                .question(question)
                .build();
    }

    public AnswerSaveRequestDto() {
    }
}
