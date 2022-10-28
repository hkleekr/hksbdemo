package com.example.hksbdemo.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Data
@Entity
public class SiteUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true)
    @NotEmpty(message = "사용자 ID는 필수항목입니다." )
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

    @Builder
    public SiteUser(String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
