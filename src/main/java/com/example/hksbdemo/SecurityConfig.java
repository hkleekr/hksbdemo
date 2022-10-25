package com.example.hksbdemo;

import com.example.hksbdemo.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter; // 스프링 시큐리티는 사이트의 콘텐츠가 다른 사이트에 포함되지 않도록 하기 위해 X-Frame-Options 헤더값을 사용하여 이를 방지한다. (clickjacking 공격을 막기위해 사용함), // 이 문제의 해결을 위해 추가 10/21
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig  {   // "extends WebSecurityConfigurerAdapter" 이 부분 제거해 둠 10/21, // security의 설정관련 클래스, 오버라이딩 해서 사용,

    private final UserSecurityService userSecurityService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").permitAll()  // 모든 인증되지 않은 요청을 허락한다는 의미 로그인을 하지 않더라도 모든 페이지에 접근가능
                .and()
                .csrf().disable()  //.ignoringAntMatchers("/h2-console/**") h2콘솔이 아니라서 disable()을 사용했음
//        .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)) //URL 요청시 X-Frame-Options 헤더값을 sameorigin으로 설정하여 오류가 발생하지 않도록 했다
        .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/board")
        .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/board")
                .invalidateHttpSession(true)  // 로그아웃시 생성된 사용자 세션 삭제
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //시큐리티 인증 담당
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Override  // 상속이 아니므로 @Override 불가능함
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()// 세션을 사용하지 않고 JWT 토큰을 활용하여 진행, csrf토큰검사를 비활성화
//                .authorizeRequests() // 인증절차에 대한 설정을 진행
//                .antMatchers("/board", "/error/*", "/login", "/signUp", "/home").permitAll() // 설정된 url은 인증되지 않더라도 누구든 접근 가능
//                .anyRequest().authenticated()// 위 페이지 외 인증이 되어야 접근가능(ROLE에 상관없이)
//                .and()
//                .formLogin()// form로그인 인증기능이 작동함
//                .loginPage("/login.html")  // 접근이 차단된 페이지 클릭시 이동할 url
//                .defaultSuccessUrl("/board")  // 로그인 성공 후 이동 페이지
//                .failureUrl("/loginfail.html?error=true")
//                .usernameParameter("username")      // view form 태그 내에 로그인 할 id 에 맵핑되는 name ( form 의 name )
//                .passwordParameter("password")      // view form 태그 내에 로그인 할 password 에 맵핑되는 name ( form 의 name )
//                .loginProcessingUrl("/login")  // 로그인 Form Action Url
////                .successHandler(loginSuccessHandler()) // 로그인 성공시 실행되는 메소드
////                .failureHandler(loginfailureHandler()) // 로그인 실패시 실행되는 메소드
//                .permitAll()  // 사용자 정의 로그인 페이지 접근 권한 승인
//                .and()
//                .logout() // 로그아웃 설정
//                .logoutUrl("/logout") // 로그아웃시 맵핑되는 url, alert으로해도 될 듯
//                .logoutSuccessUrl("/board") // 로그아웃 성공시 리다이렉트 주소
//                .invalidateHttpSession(true); // 세션 clear

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}