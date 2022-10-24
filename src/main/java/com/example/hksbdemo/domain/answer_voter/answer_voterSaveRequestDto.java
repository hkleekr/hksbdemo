package com.example.hksbdemo.domain.answer_voter;

import com.example.hksbdemo.domain.Answer;
import com.example.hksbdemo.domain.SiteUser;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class answer_voterSaveRequestDto {

    private Answer answer;
    private SiteUser site_user;

    @Builder

    public answer_voterSaveRequestDto(Answer answer, SiteUser site_user) {
        this.answer = answer;
        this.site_user = site_user;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public answer_voter toEntity() {
        return answer_voter.builder()
                .answer(answer)
                .site_user(site_user)
                .build();
    }
}
