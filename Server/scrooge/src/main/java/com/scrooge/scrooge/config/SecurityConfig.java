package com.scrooge.scrooge.config;

import com.scrooge.scrooge.config.jwt.JwtAuthenticationFilter;
import com.scrooge.scrooge.config.jwt.JwtAuthenticationProvider;
import com.scrooge.scrooge.config.jwt.JwtTokenProvider;
import com.scrooge.scrooge.config.jwt.RefreshTokenFilter;
import com.scrooge.scrooge.service.member.CustumMemberDetailsService;
import com.scrooge.scrooge.service.member.MemberService;
import com.scrooge.scrooge.service.member.TokenService;
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

    private final TokenService tokenService;

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
//                .addFilterBefore(new RefreshTokenFilter(tokenService, jwtTokenProvider), JwtAuthenticationFilter.class)
                .build();
    }

}
