package com.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 간단한 인메모리 사용자 정의 (실제 프로젝트에서는 데이터베이스를 통한 사용자 관리 필요)
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 관리자 계정 추가
        manager.createUser(User.withUsername("admin")
                .password("{noop}adminpassword") // {noop}는 패스워드 인코딩을 사용하지 않음을 의미
                .roles("ADMIN")
                .build());
        // 일반 사용자 계정 추가
        manager.createUser(User.withUsername("user")
                .password("{noop}userpassword")
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF 보호 비활성화 (필요에 따라 설정)
                .authorizeHttpRequests(auth -> auth
                        /*.requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")*/
                        .anyRequest().permitAll()
                )
                .httpBasic(); // 기본 인증 사용

        return http.build();
    }
}
