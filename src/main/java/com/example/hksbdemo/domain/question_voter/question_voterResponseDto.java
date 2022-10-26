package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import lombok.Data;

@Data

public class question_voterResponseDto {
    private Question question;
    private SiteUser siteUser;

    public question_voterResponseDto(Question_Voter entity) {
        this.question = entity.getQuestion();
        this.siteUser = entity.getSiteUser();
    }
}
