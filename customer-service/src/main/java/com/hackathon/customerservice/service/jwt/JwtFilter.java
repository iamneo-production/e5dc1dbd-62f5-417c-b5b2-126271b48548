package com.hackathon.customerservice.service.jwt;

import com.hackathon.customerservice.model.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter {

    private static final String CLIENT_ID="ClientCustomer";
    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public TokenResponse generateToken(java.lang.String customerName, int customerId) {
        Map<String, Object> claims=new HashMap<>();
        claims.put("customerName", customerName);
        claims.put("customerId", customerId);
        return createToken(claims);
    }

    private TokenResponse createToken(Map<String, Object> claims) {
        TokenResponse tokenResponse=new TokenResponse( Jwts.builder()
                .setClaims(claims)
                .setSubject(CLIENT_ID)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact());
        return tokenResponse;

    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
