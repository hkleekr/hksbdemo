package com.example.hksbdemo.domain.answer_voter;

import com.example.hksbdemo.domain.answer.answer;
import com.example.hksbdemo.domain.answer_voter.answer_voter;
import com.example.hksbdemo.domain.site_user.site_user;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class answer_voterSaveRequestDto {

    private com.example.hksbdemo.domain.answer.answer answer;
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    @Builder

    public answer_voterSaveRequestDto(com.example.hksbdemo.domain.answer.answer answer, com.example.hksbdemo.domain.site_user.site_user site_user) {
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
