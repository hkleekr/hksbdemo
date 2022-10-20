package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.site_user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserRepository extends JpaRepository <SiteUser, Long>{
    SiteUser findById(String id);
}

//Long 맞나 확인 필요함