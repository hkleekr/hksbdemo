package com.example.hksbdemo.domain.site_user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name= "site_user")
public class site_user {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Builder

    public site_user(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
