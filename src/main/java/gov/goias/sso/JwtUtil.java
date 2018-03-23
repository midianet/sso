package gov.goias.sso;

import gov.goias.sso.domain.Person;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${signingKey}")
    private String signingKey;

    public String generateToken(Person person) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Claims c = Jwts.claims()
        .setSubject(person.getName());
        c.put("email",person.getEmail());
        c.put("cpf",person.getCpf());
        c.put("roles",person.getRoles().toArray());
        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setClaims(c)
                .signWith(SignatureAlgorithm.HS512, signingKey);
        return builder.compact();
    }
//
//    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
//        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
//        if(token == null) return null;
//        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
//    }

}