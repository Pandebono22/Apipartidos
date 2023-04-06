package com.fesc.apipartidos.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Collections;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
    public static final String TOKEN_SECRETO = "LAopzruoXHu8FPe/QP3Ongpga4fYVvAvECHL2Hsa7QGeYnKOJOHRYwyCSVjKDfprXJleVh99leqs3E0335rTnA==";

    public static String createToken(String nombre, String email) {

        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;

        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(TOKEN_SECRETO.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TOKEN_SECRETO.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

        } catch (JwtException e) {
            return null;
        }
    }

}
