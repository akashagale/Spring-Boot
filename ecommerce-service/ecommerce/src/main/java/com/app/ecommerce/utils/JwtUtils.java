package com.app.ecommerce.utils;

import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.handler.GlobalExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    private String jwtSecret = "qazxcvbnm1234567890QAZXCVBNMdgdgdgtryjghfeetrfgdfgdgtryrtygrytrtyffweferyydgdgreretretr";
    private long jwtExpirationMs = 300000;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    // validate Jwt token
    public boolean validateJwtToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new ECommerceException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new ECommerceException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new ECommerceException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new ECommerceException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    public String getUserNameFromJwtToken(String jwt) {
        String subject = Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(jwt)
                .getBody().getSubject();
        logger.debug("JwtUtils inside getUserNameFromJwtToken {}", subject);
        return subject;
    }

    public String generateToken(Authentication authenticate) {
        String username = authenticate.getName();
        Date currentdate = new Date();
        Date expirationDate = new Date(currentdate.getTime() + jwtExpirationMs);
        String token = Jwts.builder().setIssuedAt(currentdate)
                .setExpiration(expirationDate)
                .setSubject(username)
                .signWith(key())
                .compact();

        logger.debug("JwtUtils inside generateToken {}", token);
        return token;
    }
}
