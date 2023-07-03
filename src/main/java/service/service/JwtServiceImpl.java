package service.service;

import service.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${jwt.expiration}")
    private long expirationDuration;
    @Value("${jwt.refresh}")
    private long refreshDuration;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expirationDuration*1000*60))
                .setClaims(new HashMap<>())
                .setSubject(user.getUsername())
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }
    @Override
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    private <T> T extractClaims(String token, Function<Claims,T > claimResolver){
        return claimResolver.apply(Jwts
                .parser()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret)))
                .parseClaimsJws(token)
                .getBody());
    }
}
