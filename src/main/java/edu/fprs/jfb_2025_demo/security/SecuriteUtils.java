package edu.fprs.jfb_2025_demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SecuriteUtils {

    @Value("${jwt.secret}")
    String jwtSecret;

    public String getRole(AppUserDetails userDetails) {

        //        //récupérer le role de la personne
//        String role = userDetails.getAuthorities().stream()
        return userDetails.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .findFirst()
                .orElse(null);

    }

    public String generateToken(AppUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(Map.of("role", userDetails.getAuthorities()))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//                .setExpiration()
                .compact();
    }

    public String getSubjectFromJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
