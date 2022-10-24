package com.example.hksbdemo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class QuestionSaveRequestDto {

    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private String subject;
    private SiteUser site_user;

    @Builder

    public QuestionSaveRequestDto(String content, LocalDateTime create_date, LocalDateTime modify_date, String subject, SiteUser site_user) {
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.subject = subject;
        this.site_user = site_user;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public Question toEntity() {
        return Question.builder()
                .content(content)
                .create_date(create_date)
                .modify_date(modify_date)
                .subject(subject)
                .site_user(site_user)
                .build();
    }

}
