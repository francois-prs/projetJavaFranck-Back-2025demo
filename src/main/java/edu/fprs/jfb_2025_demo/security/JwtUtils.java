package edu.fprs.jfb_2025_demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    public String generateToken(AppUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, "azerty")
                .compact();
    }

    public String getSubjectFromJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
