package com.example.hksbdemo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AnswerSaveRequestDto {

    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private SiteUser site_user;
    private Question question;

//    Dto에서 요청 필요한 부분을 entity로 보냄
    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .create_date(create_date)
                .modify_date(modify_date)
                .site_user(site_user)
                .question(question)
                .build();
    }
}
