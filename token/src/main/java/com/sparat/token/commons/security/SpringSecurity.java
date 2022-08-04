package com.sparat.token.commons.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(WebSecurity web) {
    // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
    web
            .ignoring()
            .antMatchers("/h2-console/**");
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().cors().disable().headers().frameOptions().disable();

    http.csrf()
            .ignoringAntMatchers("/user/**");

    http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/post").permitAll()
            .antMatchers(HttpMethod.GET, "/api/post/{id}").permitAll()
            .antMatchers(HttpMethod.POST, "/user/signUp").permitAll()
            .antMatchers("/api/post/**")
            .access("hasRole(ROLE_MEMBER)")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/user/signIn")
            .loginProcessingUrl("/user/signIn")
            .permitAll()
            .and()
            .logout()
            // 로그아웃 요청 처리 URL
            .logoutUrl("/user/logout")
            .permitAll();
//            .httpBasic().disable();
  }
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
