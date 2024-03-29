package com.example.salarying.global.config;


import com.example.salarying.global.jwt.JwtAuthorizationFilter;
import com.example.salarying.global.jwt.JwtExceptionEntryPoint;
import com.example.salarying.global.jwt.auth.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthTokenProvider authTokenProvider;

    String[] permitUrl = {"/oauth2/**", "/", "/users/login/**","/admin/login/**", "/signup/**", "/swagger-ui/**", "/api-docs/**"
    ,"/swagger-ui/index.html"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .cors()//기본 cors 설정
                .and()
                .csrf().disable()
                .formLogin().disable() //formLogin 인증 비활성화
                .httpBasic().disable() //httpBasic 인증 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers(permitUrl)
                .permitAll()
                .antMatchers("/terms/**", "/admin/*").hasAnyAuthority("ADMIN","SUPERADMIN")
                .antMatchers("/applicants/**","/recruiting/**","/users/**").hasAuthority("USER")
                .anyRequest().authenticated();

        http
                .apply(new customConfig());

        http
                .exceptionHandling()
                .authenticationEntryPoint(new JwtExceptionEntryPoint());

        http
                .logout();

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public class customConfig extends AbstractHttpConfigurer<customConfig, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilterAfter(new JwtAuthorizationFilter(authenticationManager, authTokenProvider), CorsFilter.class);
        }
    }


}
