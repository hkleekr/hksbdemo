package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.site_user.site_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface site_userRepository extends JpaRepository <site_user, Long>{
    site_user findByEmail(String email);
}

//Long 맞나 확인 필요함