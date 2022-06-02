package recorder.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 다음 유틸리티 클래스는 사용자가 성공적으로 로그인한 후
 * JWT를 생성하고 요청의 Authorization 헤더에 전송된 JWT를 검증하는 데 사용됩니다.
 */

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication authentication) {

		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder()
			.setSubject(Long.toString(userPrincipal.getId()))
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(getSignKey())
			.compact();
	}

	public Long getUserIdFromJWT(String token) {

		Claims claims = Jwts.parserBuilder()
			.setSigningKey(getSignKey())
			.build()
			.parseClaimsJws(token)
			.getBody();

		/*
		Claims claims = Jwts.parserBuilder()
			.setSigningKey(jwtSecret)
			.parseClaimsJws(token)
			.getBody();*/

		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}

	private Key getSignKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
}
