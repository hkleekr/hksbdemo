package com.example.hksbdemo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class QuestionResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private String subject;
    private SiteUser site_user;
    private String responseCode;
}
