package com.abdulmajid.expensetracker.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service

public class JwtService {

    // SECRET KEY
    private static final String SECRET_KEY =
            "mySuperSecretKeyForJwtAuthentication123456";

    // TOKEN EXPIRATION (24 HOURS)
    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24;

    // GENERATE SIGNING KEY
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes()
        );
    }

    // GENERATE TOKEN
    public String generateToken(
            String email
    ) {

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )

                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }

    // EXTRACT EMAIL FROM TOKEN
    public String extractEmail(
            String token
    ) {

        return extractClaims(token)
                .getSubject();
    }

    // EXTRACT ALL CLAIMS
    private Claims extractClaims(
            String token
    ) {

        return Jwts.parser()

                .verifyWith(getSigningKey())

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }

    // VALIDATE TOKEN
    public boolean isTokenValid(
            String token,
            String email
    ) {

        String extractedEmail =
                extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    // CHECK TOKEN EXPIRATION
    private boolean isTokenExpired(
            String token
    ) {

        return extractClaims(token)

                .getExpiration()

                .before(new Date());
    }
}