package com.example.hksbdemo.domain.answer;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AnswerSaveRequestDto {

    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private com.example.hksbdemo.domain.site_user.site_user site_user;
    private com.example.hksbdemo.domain.question.question question;

    @Builder
    public AnswerSaveRequestDto(String content, LocalDateTime create_date, LocalDateTime modify_date, com.example.hksbdemo.domain.site_user.site_user site_user, com.example.hksbdemo.domain.question.question question) {
        this.content = content;
        this.create_date = create_date;
        this.modify_date = modify_date;
        this.site_user = site_user;
        this.question = question;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public answer toEntity() {
        return answer.builder()
                .content(content)
                .create_date(create_date)
                .modify_date(modify_date)
                .site_user(site_user)
                .question(question)
                .build();
    }
}
