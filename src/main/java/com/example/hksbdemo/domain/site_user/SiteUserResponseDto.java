package com.example.hksbdemo.domain.site_user;

import lombok.Data;

@Data
public class SiteUserResponseDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String responseCode;

    public SiteUserResponseDto(SiteUser entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.username = entity.getUsername();
    }

    public SiteUserResponseDto() { } // 기본생성자

    public SiteUserResponseDto(Long id, String email, String password, String username, String responseCode) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.responseCode = responseCode;
    }
}
