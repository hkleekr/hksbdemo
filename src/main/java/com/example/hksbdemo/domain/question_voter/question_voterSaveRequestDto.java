package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.site_user.SiteUser;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class question_voterSaveRequestDto {
    private Question question;
    private SiteUser site_user;

    @Builder

    public question_voterSaveRequestDto(Question question, SiteUser site_user) {
        this.question = question;
        this.site_user = site_user;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public question_voter toEntity() {
        return question_voter.builder()
                .question(question)
                .site_user(site_user)
                .build();
    }
}
