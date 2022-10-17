package com.example.hksbdemo.service.site_user;

import com.example.hksbdemo.repository.site_userRepository;
import com.example.hksbdemo.domain.site_user.site_userSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class site_userService {

    private final site_userRepository site_userRepository;

    @Transactional
    public Long save(site_userSaveRequestDto requestDto) { return site_userRepository.save(requestDto.toEntity()).getId();}
}
