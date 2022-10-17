package com.example.hksbdemo.domain.question_voter;

import com.example.hksbdemo.domain.question.question;
import com.example.hksbdemo.domain.question_voter.question_voter;
import lombok.Data;

@Data

public class question_voterResponseDto {
    private question question;
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    public question_voterResponseDto(question_voter entity) {
        this.question = entity.getQuestion();
        this.site_user = entity.getSite_user();
    }
}
