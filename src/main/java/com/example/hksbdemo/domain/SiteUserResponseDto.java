package com.example.hksbdemo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SiteUserResponseDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private String responseCode;
}
