package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class question_voterSaveRequestDto {
    private Question question;
    private SiteUser siteUser;

    @Builder

    public question_voterSaveRequestDto(Question question, SiteUser siteUser) {
        this.question = question;
        this.siteUser = siteUser;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public Question_Voter toEntity() {
        return Question_Voter.builder()
                .question(question)
                .siteUser(siteUser)
                .build();
    }
}
