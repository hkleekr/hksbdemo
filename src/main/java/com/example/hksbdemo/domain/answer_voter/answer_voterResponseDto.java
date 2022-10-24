package com.example.hksbdemo.domain.answer_voter;

import com.example.hksbdemo.domain.Answer;
import com.example.hksbdemo.domain.SiteUser;
import lombok.Data;


@Data
public class answer_voterResponseDto {

    private Answer answer;
    private SiteUser site_user;

    public answer_voterResponseDto(answer_voter entity) {
        this.answer = entity.getAnswer();
        this.site_user = entity.getSite_user();
    }
}
