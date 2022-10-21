package com.example.hksbdemo.domain.site_user;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
public class SiteUserSaveRequestDto {

    private Long id;
    private String username;
    private String email;
    private String password;

    @Builder
    public SiteUserSaveRequestDto(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    // Dto에서 필요한 부분을 entity화 시킴
    public SiteUser toEntity() {
        return SiteUser.builder()
                .email(email)
                .password(password)
                .username(username)
                .build();
    }
}
