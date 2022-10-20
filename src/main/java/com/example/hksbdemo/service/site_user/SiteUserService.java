package com.example.hksbdemo.service.site_user;

import com.example.hksbdemo.repository.SiteUserRepository;
import com.example.hksbdemo.domain.site_user.SiteUserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void insert(SiteUserSaveRequestDto siteUserSaveRequestDto) {
        siteUserSaveRequestDto.setPassword(bCryptPasswordEncoder.encode(siteUserSaveRequestDto.getPassword()));
        siteUserRepository.save(siteUserSaveRequestDto.toEntity());
    }

    @Bean // 만든 이유: ErrorMessage => Consider defining a bean of type 'org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder' in your configuration.
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public Long save(SiteUserSaveRequestDto requestDto) { return siteUserRepository.save(requestDto.toEntity()).getId();}
}
