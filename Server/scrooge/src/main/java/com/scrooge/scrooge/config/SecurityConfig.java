package com.scrooge.scrooge.config;

import com.scrooge.scrooge.config.jwt.JwtAuthenticationFilter;
import com.scrooge.scrooge.config.jwt.JwtAuthenticationProvider;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.service.member.CustumMemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final CustumMemberDetailsService memberDetailsService;
    private final JwtAuthenticationProvider authenticationProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .formLogin().disable()
                .httpBasic().disable()

                .authorizeRequests()
                .antMatchers("/member/signup").permitAll()
                .antMatchers("/member/login").permitAll()

//                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
