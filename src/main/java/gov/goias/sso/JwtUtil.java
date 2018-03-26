package gov.goias.sso;

import gov.goias.sso.domain.Person;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtil {
    
    @Value("${cookie}")
    private String token;
    
    @Value("${key}")
    private String key;

    public String generateToken(Person person) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Claims c = Jwts.claims()
        .setSubject(person.getName());
        c.put("email",person.getEmail());
        c.put("cpf",person.getCpf());
        c.put("authorities",person.getRoles().toArray());
        JwtBuilder builder = Jwts.builder()
            .setIssuedAt(now)
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setClaims(c)
            .signWith(SignatureAlgorithm.HS512, key);
        return builder.compact();
    }

    public void validate(String token){
        Optional.ofNullable(token).ifPresent(s ->{
            Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        });
    }
    
}