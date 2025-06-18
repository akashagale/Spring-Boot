package com.app.ecommerce.utils;

import com.app.ecommerce.entity.User;
import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.handler.GlobalExceptionHandler;
import com.app.ecommerce.repo.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

    private String jwtSecret = "qazxcvbnm1234567890QAZXCVBNMdgdgdgtryjghfeetrfgdfgdgtryrtygrytrtyffweferyydgdgreretretr";
    private long jwtExpirationMs = 3000000;
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    @Autowired
    private UserRepository userRepository;

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

    public Integer getLoggedInUsername() {
        User loggedInUser = getLoggedInUser();
        Integer id = (loggedInUser.getId());
            if (id>0) {
                return id;
            }
        return 0;
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();  // this returns the username (typically from the `sub` claim)
            Optional<User> user = userRepository.findByEmail(email);
//            user.get().setUsername(null);
//            user.get().setPassword(null);
            if (user.isPresent()) {
                return user.get();
            }
        }
        return null;
    }
}
