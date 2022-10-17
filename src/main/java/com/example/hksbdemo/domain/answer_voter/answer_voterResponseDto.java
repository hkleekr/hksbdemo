package com.example.hksbdemo.domain.answer_voter;

import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.domain.answer_voter.answer_voter;
import lombok.Data;


@Data
public class answer_voterResponseDto {

    private answer answer;
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    public answer_voterResponseDto(answer_voter entity) {
        this.answer = entity.getAnswer();
        this.site_user = entity.getSite_user();
    }
}
