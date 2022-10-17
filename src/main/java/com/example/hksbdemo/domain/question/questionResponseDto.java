package com.example.hksbdemo.domain.question;

import com.example.hksbdemo.domain.question.question;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class questionResponseDto {
    private Integer id;
    private String content;
    private LocalDateTime create_date;
    private LocalDateTime modify_date;
    private String subject;
    private com.example.hksbdemo.domain.site_user.site_user site_user;

    public questionResponseDto(question entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date();
        this.modify_date = entity.getModify_date();
        this.subject = entity.getSubject();
        this.site_user = entity.getSite_user();
    }
}
