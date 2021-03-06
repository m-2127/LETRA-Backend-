package com.bitrebels.letra.security.jwt;

import com.bitrebels.letra.repository.UserRepository;
import com.bitrebels.letra.services.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.bitrebels.letra.security.jwt.SecurityConstraints.EXPIRATION_TIME;
import static com.bitrebels.letra.security.jwt.SecurityConstraints.SECRET;

@Component
public class JwtProvider {

    @Autowired
    UserRepository userRepo;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        String name = userRepo.findById(userPrincipal.getId()).get().getName();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername())) //getUsername returns the email
		                .claim("id",userPrincipal.getId() )
                        .claim("name",name)
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
		                .signWith(SignatureAlgorithm.HS512, SECRET)
		                .compact();
    }

    public String generateJwtToken(UserPrinciple userPrincipal) {

        String name = userRepo.findById(userPrincipal.getId()).get().getName();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())) //getUsername returns the email
                .claim("id",userPrincipal.getId() )
                .claim("name",name)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(SECRET)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}