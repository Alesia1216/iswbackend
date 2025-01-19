package net.ausiasmarch.iswart.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import net.ausiasmarch.iswart.exception.UnauthorizedAccessException;

@Service
public class JWTService {

    @Value("${jwt.subject}")
    private String SUBJECT;
    @Value("${jwt.issuer}")
    private String ISSUER;
    @Value("${jwt.secret}")
    private String SECRET_KEY;


    public SecretKey getSecretKey() {
        String secretKey = SECRET_KEY;
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Map<String, String> claims) {
        return Jwts.builder()
            .id(UUID.randomUUID().toString())
            .claims(claims)
            .subject(SUBJECT)
            .issuer(ISSUER)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 6000000))
            .signWith( getSecretKey(), Jwts.SIG.HS256)
            .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        //TODO try catch
        try{
            return Jwts.parser().verifyWith(getSecretKey()).build().parseClaimsJws(token).getBody();
        }catch (Exception e){
            throw new UnauthorizedAccessException("Token inválido");
        }
    }

    public String validateToken(String sToken){  

        Claims oClaims = getAllClaimsFromToken(sToken);

        if(oClaims.getExpiration().before(new Date())) {
            //TODO los return null tienen que ser thows de news exceptions que hay que crear
            return null;
        }

        if(oClaims.getIssuedAt().after(new Date())) {
            return null;
        }

        if(!oClaims.getIssuer().equals(ISSUER)) {
            return null;
        }

        if(!oClaims.getSubject().equals(SUBJECT)) {
            return null;
        }

        return oClaims.get("email", String.class);

    }
    

}