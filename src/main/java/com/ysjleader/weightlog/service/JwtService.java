package com.ysjleader.weightlog.service;

import com.ysjleader.weightlog.dto.VerifyEmailDTO;
import com.ysjleader.weightlog.dto.request.VerifyEmailRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    private String secretKey;

    @Value("${jwt.secret-key}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String create(VerifyEmailRequestDTO requestBody) {
        Date date = new Date();
        Key key = Keys.hmacShaKeyFor(generateKey());
        Claims claims = Jwts.claims().setSubject(requestBody.getEmail());
        String jws = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .claim("homeURL", requestBody.getHomeURL())
                .claim("redirectURL", requestBody.getRedirectURL())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() +  24 * 60 * 60 * 1000L))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return jws;
    }

    private boolean isUsable(String jws) {
        log.info("jws : {} ", jws);

        return getJwsClaims(jws) != null;
    }

    private Jws<Claims> getJwsClaims(String jws) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(jws);
        } catch (Exception e) {
            throw new IllegalArgumentException("불법 jwt = " + jws.toString());
        }

        return claims;
    }

    public VerifyEmailDTO verifyEmail(String token) {
        isUsable(token);
        Jws<Claims> jwsClaims = getJwsClaims(token);

        return VerifyEmailDTO.builder()
                .email(jwsClaims.getBody().getSubject())
                .redirectURL(jwsClaims.getBody().get("redirectURL", String.class))
                .homeURL(jwsClaims.getBody().get("homeURL", String.class))
                .build();

    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("generate key fail : {} ", e);
        }

        return key;
    }
}