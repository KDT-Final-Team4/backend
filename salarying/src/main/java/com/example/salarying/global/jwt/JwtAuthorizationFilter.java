package com.example.salarying.global.jwt;


import com.example.salarying.global.jwt.auth.AuthToken;
import com.example.salarying.global.jwt.auth.AuthTokenProvider;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private String TOKEN_PREFIX = "Bearer ";

    private AuthTokenProvider authTokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthTokenProvider authTokenProvider) {
        super(authenticationManager);
        this.authTokenProvider = authTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        log.info("JWT Filter is running...");
        // 요청에서 토큰 가져오기
        String tokenStr = parseBearerToken(request);
        if(tokenStr != null && !tokenStr.equalsIgnoreCase("null")){
            try{
                //string -> AuthToken
                AuthToken authToken = authTokenProvider.convertAuthToken(tokenStr);

                //토큰 검증 (jwt이므로 인가서버에 요청하지 않고도 검증 가능)
                AbstractAuthenticationToken authentication = authTokenProvider.getAuthentication(authToken);
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                //토큰 타입 검증
                if(userDetails != null && !userDetails.getJwtType().equals(JwtType.ACCESS)) throw new JwtException("Access Token type 이 아닙니다");

                //사용자 인증정보 SecurityContextHolder에 등록(요청 끝날때까지 들고있어야함)
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            } catch (JwtException ex){
                log.info("유효기간이 만료되었거나 구조가 잘못되었거나 파싱 실패한 토큰입니다.", ex);
                throw new AuthenticationException("AuthenticationException 에러");
            }
        }

        filterChain.doFilter(request, response);
    }

    public String parseBearerToken(HttpServletRequest request) {
        // http 헤더 파싱해 토큰 얻음
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        //AccessToken인지 검증, 토큰 파싱
        if(StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_PREFIX)){
            return authorization.replace(TOKEN_PREFIX,"");
        }
        return null;
    }
}