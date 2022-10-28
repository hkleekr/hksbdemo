package com.example.hksbdemo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SiteUserSaveRequestDto {

    private Long id;
    private String username;
    private String email;
    private String password;

// Dto에서 요청에 필요한 부분을 entity로
    public SiteUser toEntity() {
        return SiteUser.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
