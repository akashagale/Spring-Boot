package com.springboot.blog.security.JWT;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.springboot.blog.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret; 
	
	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;
	
	
	// generate jwt token
	public String generateToken(Authentication authentication) {
		String username= authentication.getName();
		
		Date currentDate = new Date();
		
		Date expireDate= new Date(currentDate.getTime() + jwtExpirationDate);
		
		String token = Jwts.builder()
		.setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(expireDate)
		.signWith(key())
		.compact();
		
		return token;
	}
	
	// decode jwtSecret
	private Key key() {
		return Keys.hmacShaKeyFor(
				Decoders.BASE64.decode(jwtSecret));
	}
	
	
	// get username from token
	public String getUsername(String token) {
		Claims claims = Jwts.parserBuilder()
		.setSigningKey(key())
		.build()
		.parseClaimsJws(token)
		.getBody();
		
		String username=claims.getSubject();
		
		return username;
		
	}
	
	// validate JWT token
	public boolean ValidateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key())
			.build()
			.parse(token);
			
			return true;
		} catch (MalformedJwtException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid Jwt token");
		} catch (ExpiredJwtException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired Jwt token");
		} catch (UnsupportedJwtException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported Jwt token");
		} catch (IllegalArgumentException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Jwt claims String is empty");
		}
	}

}
