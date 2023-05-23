package com.practice.snsproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private final UserDetailsService userDetailsService;
    private final Long expireTimeMs;
    private String secretKey;

    public JwtTokenProvider(
            UserDetailsService userDetailsService,
            @Value("${jwt.token.secret}")String secretKey,
            @Value("${jwt.token.expireTimeMs}")long expireTimeMs) {
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
        this.expireTimeMs = expireTimeMs;
    }

    @PostConstruct
    protected void init(){
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작 : {} ", this.secretKey);
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료 : {}", secretKey);
    }

    public String createToken(String userName, String authorities) {
        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("role", authorities);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //토큰의 만료여부 검증
    public boolean isExpired(String token){
        Date expiredDate = extractClaims(token).getExpiration();
        return expiredDate.before(new Date());
    }

    //토큰에 저장된 userName 반환
    public String getUserName(String token){
        return extractClaims(token).getSubject();
    }

    //토큰 내용 추출
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    //토큰 기반으로 인증 정보를 가져오는 메서드
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
        log.info("[getAuthentication userDetails.getUsername : {}]", userDetails.getUsername());
        userDetails.getAuthorities().stream().forEach(userAuth -> log.info("[getAuentication Userdetails.getGrantedAuthorities = {}]", userAuth.getAuthority()));
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities()); //userName, Role이 담기도록 구현
    }

}
