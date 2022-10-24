package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.Question;
import com.example.hksbdemo.domain.SiteUser;
import lombok.Data;

@Data

public class question_voterResponseDto {
    private Question question;
    private SiteUser site_user;

    public question_voterResponseDto(question_voter entity) {
        this.question = entity.getQuestion();
        this.site_user = entity.getSite_user();
    }
}
