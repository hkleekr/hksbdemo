package com.example.hksbdemo.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration   // 환경 구성파일임을 알리고 @Bean(개발자가 작성한 메서드의 return되는 객체를 Bean으로 만듦) Bean(Spring IoC 컨테이너가 관리하는 자바 객체)임을 알릴께
@EnableWebSecurity  // 웹보안 활성화를 위한 annotation
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {   // security의 설정관련 클래스, 오버라이딩 해서 사용

//    로그인 관련 메서드들: return 클래스들은 커스텀으로 구현할 것
//    @Bean
//    public AuthenticationProvider authenticationProvider(){return new LoginAuthenticationProvider();}
//
//    @Bean
//    public AuthenticationSuccessHandler successHandlerHandler() {
//        return new LoginSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler failureHandlerHandler() {
//        return new LoginFailureHandler();
//    }


//    정적 페이지는 어디서든 접근가능하도록 설정하는 메서드
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()// 세션을 사용하지 않고 JWT 토큰을 활용하여 진행, csrf토큰검사를 비활성화
                .authorizeRequests() // 인증절차에 대한 설정을 진행
                .antMatchers("/board", "/error/*", "/login", "/signUp", "/home").permitAll() // 설정된 url은 인증되지 않더라도 누구든 접근 가능
                .anyRequest().authenticated()// 위 페이지 외 인증이 되어야 접근가능(ROLE에 상관없이)
                .and()
                .formLogin()// form로그인 인증기능이 작동함
                .loginPage("/login.html")  // 접근이 차단된 페이지 클릭시 이동할 url
                .defaultSuccessUrl("/board")  // 로그인 성공 후 이동 페이지
                .failureUrl("/loginfail.html?error=true")
                .usernameParameter("username")      // view form 태그 내에 로그인 할 id 에 맵핑되는 name ( form 의 name )
                .passwordParameter("password")      // view form 태그 내에 로그인 할 password 에 맵핑되는 name ( form 의 name )
                .loginProcessingUrl("/login")  // 로그인 Form Action Url
//                .successHandler(loginSuccessHandler()) // 로그인 성공시 실행되는 메소드
//                .failureHandler(loginfailureHandler()) // 로그인 실패시 실행되는 메소드
                .permitAll()  // 사용자 정의 로그인 페이지 접근 권한 승인
                .and()
                .logout() // 로그아웃 설정
                .logoutUrl("/logout") // 로그아웃시 맵핑되는 url, alert으로해도 될 듯
                .logoutSuccessUrl("/board") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true); // 세션 clear
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
