package com.example.hksbdemo.service.site_user;

import com.example.hksbdemo.domain.site_user.SiteUser;
import com.example.hksbdemo.domain.site_user.SiteUserResponseDto;
import com.example.hksbdemo.domain.site_user.SiteUserSaveRequestDto;
import com.example.hksbdemo.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(SiteUserSaveRequestDto requestDto, SiteUserResponseDto responseDto) {
        Long getId = siteUserRepository.save(requestDto.toEntity()).getId();
        String result = "";
        boolean su = siteUserRepository.existsById(getId);
        if(su == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

}
