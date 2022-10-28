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

//    Dto에서 요청 필요한 부분을 entity로 보냄, 변경할 수 없는 부분인, id, answerList는 제외시킴
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
