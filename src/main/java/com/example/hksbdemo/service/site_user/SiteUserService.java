package com.example.hksbdemo.service.site_user;

import com.example.hksbdemo.domain.site_user.SiteUser;
import com.example.hksbdemo.domain.site_user.SiteUserResponseDto;
import com.example.hksbdemo.domain.site_user.SiteUserSaveRequestDto;
import com.example.hksbdemo.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(SiteUserSaveRequestDto requestDto, SiteUserResponseDto responseDto) {
        SiteUser su = new SiteUser();
        su.setPassword(passwordEncoder.encode(requestDto.getPassword()));  //왜 암호로 바뀌지 않는가!!
        su.setUsername(requestDto.getUsername());
        su.setEmail(requestDto.getEmail());
        Long getId = siteUserRepository.save(requestDto.toEntity()).getId();
        String result = "";
        boolean suCheck = siteUserRepository.existsById(getId);
        if(suCheck == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }
    @Transactional
    public void update(SiteUserSaveRequestDto requestDto, SiteUserResponseDto responseDto) {
        Optional<SiteUser> os = Optional.ofNullable(siteUserRepository.findByusername(requestDto.getUsername()));
        SiteUser s = os.get();
        s.setPassword(passwordEncoder.encode(requestDto.getPassword()));  //암호화 해서 저장
        s.setEmail(requestDto.getEmail());
        siteUserRepository.save(s);
        String result = "";
        boolean modifySiteUser = siteUserRepository.findByusername(requestDto.getUsername()).getEmail().equals(requestDto.getEmail());
        if (modifySiteUser == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

    @Transactional
    public void delete(String username, SiteUserResponseDto siteUserResponseDto) {
        Optional<SiteUser> su = Optional.ofNullable(siteUserRepository.findByusername(username));
        if(su.isPresent())
            siteUserRepository.delete(su.get());
        siteUserResponseDto.setResponseCode("성공");
        }

    @Transactional
    public Object getDetail(String username) {
        Optional<SiteUser> sd = Optional.ofNullable(siteUserRepository.findById(username));
        return  sd.get();
    }

}
