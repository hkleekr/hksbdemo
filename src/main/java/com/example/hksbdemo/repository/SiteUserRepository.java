package com.example.hksbdemo.repository;

import com.example.hksbdemo.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository extends JpaRepository <SiteUser, Long>{
    SiteUser findById(String id);

    Optional<SiteUser> findByusername(String username);

}


//Long 맞나 확인 필요함