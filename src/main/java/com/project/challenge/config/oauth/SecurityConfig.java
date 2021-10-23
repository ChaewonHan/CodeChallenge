package com.project.challenge.config.oauth;

import com.project.challenge.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http     //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .csrf().disable().headers().frameOptions().disable().and()
                //URL 별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                //권한 관리 대상을 지정하는 옵션. permitAll(): 전체 열람 권한
                .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**", "/profile").permitAll()
                //hasRole: 특정 권한을 가진 사람만 열람 권한줌
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //설정된 값 이외 나머지 URL들을 나타냄. authenticated(): 나머지 URL들을 모두 인증된 사용자들에게만 허용
                .anyRequest().authenticated()
                .and()
                //로그아웃 기능에 대한 여러 설정의 진입점
                .logout()
                //로그아웃 성공시 설정한 주소로 이동
                .logoutSuccessUrl("/")
                .and()
                //OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userInfoEndpoint()
                //소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                //리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음
                .userService(customOAuth2UserService);
    }
}
