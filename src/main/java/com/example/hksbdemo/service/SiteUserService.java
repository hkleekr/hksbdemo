package com.example.hksbdemo.service;

import com.example.hksbdemo.DataNotFoundException;
import com.example.hksbdemo.domain.SiteUser;
import com.example.hksbdemo.domain.SiteUserResponseDto;
import com.example.hksbdemo.domain.SiteUserSaveRequestDto;
import com.example.hksbdemo.repository.SiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SiteUserService {

    private final SiteUserRepository siteUserRepository;
    private final PasswordEncoder passwordEncoder;

//    회원가입, 패스워드 암호화 처리
    @Transactional
    public void save(SiteUserSaveRequestDto requestDto, SiteUserResponseDto responseDto) {
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));  // set pw 암호화 해놓고 -> 다시 save requestDto해서 원래대로 돌아 갔었음
        Long getId = siteUserRepository.save(requestDto.toEntity()).getId();
        String result = "";
        boolean suCheck = siteUserRepository.existsById(getId);
        if(suCheck == true) {
            result = "성공";
        }
        responseDto.setResponseCode(result);
    }

//    회원정보 수정
    @Transactional
    public void update(SiteUserSaveRequestDto requestDto, SiteUserResponseDto responseDto) {
        Optional<SiteUser> os = siteUserRepository.findByusername(requestDto.getUsername());
        SiteUser s = os.get();      // s는 os를 통해 username으로 이미 저장되어있던 유저를 가져온 친구
        s.setPassword(passwordEncoder.encode(requestDto.getPassword()));  //암호화 해서 저장
        s.setEmail(requestDto.getEmail());
        siteUserRepository.save(s);    //회원정보 수정완료
//        String result = "";
//        boolean modifySiteUser = s.equals(siteUserRepository.findByusername(requestDto.getEmail()));  //getemail()이어야 하는데,,확인필요
//        boolean modifySiteUser = s.equals(siteUserRepository.findByusername(requestDto.getEmail()));   //의도: 수정 전의 회원정보 값과 다를 경우 "수정 성공" -> s는 수정 완료된 친구, 수정 전의 친구를 어떻게 불러올 지 더 고민해보기
        // findByusername().get()
//        if (modifySiteUser == true) {
//            result = "성공";
//        }
        responseDto.setResponseCode("성공");  // 10/24, 수정사항 확인하지 않고, 성공이라고 표시하기로 함, 잘 동작함을 확인했지만, 수정코드를 적용하고 싶으므로, 고민할 것
    }

//    회원탈퇴
    @Transactional
    public void delete(String username, SiteUserResponseDto siteUserResponseDto) {
        Optional<SiteUser> su = siteUserRepository.findByusername(username);
        if(su.isPresent())
            siteUserRepository.delete(su.get());
        siteUserResponseDto.setResponseCode("성공");
        }

    @Transactional
    public Object getDetail(String username) {
        Optional<SiteUser> sd = Optional.ofNullable(siteUserRepository.findById(username));
        return  sd.get();
    }

//    SiteUser Entity에서 작성자 데려오기 위한 코드
    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.siteUserRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}
