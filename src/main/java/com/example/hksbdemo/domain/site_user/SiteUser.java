package com.example.hksbdemo.domain.site_user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SiteUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;



    @Builder
    public SiteUser(String email, String password, String username) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
//        this.role = role;
    }
}
