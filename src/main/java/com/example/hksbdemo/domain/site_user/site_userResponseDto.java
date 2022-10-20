package com.example.hksbdemo.domain.site_user;

import lombok.Data;

@Data
public class site_userResponseDto {
    private Long id;
    private String email;
    private String password;
    private String username;

    public site_userResponseDto(SiteUser entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.username = entity.getUsername();
    }
}
