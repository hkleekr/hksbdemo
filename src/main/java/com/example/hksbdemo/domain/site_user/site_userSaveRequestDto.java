package com.example.hksbdemo.domain.site_user;

import com.example.hksbdemo.domain.site_user.site_user;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class site_userSaveRequestDto {

    private String email;
    private String password;
    private String username;

    @Builder

    public site_userSaveRequestDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public site_user toEntity() {
        return site_user.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
