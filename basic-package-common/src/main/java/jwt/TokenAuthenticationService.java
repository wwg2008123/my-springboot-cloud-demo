package jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class TokenAuthenticationService {
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static JwtLoginFilter.JwtResult addAuthentication(HttpServletResponse res, Authentication authentication, String secret, long expirationTime) {
        Long now = System.currentTimeMillis();
        String jwt = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authentication.getAuthorities())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationTime))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
        return new JwtLoginFilter.JwtResult(jwt, TOKEN_PREFIX);
    }

    public static Authentication getAuthentication(HttpServletRequest request, String secret) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token/*.replace(TOKEN_PREFIX, "")*/)
                    .getBody();
            String user = claims.getSubject();
            List<LinkedHashMap<String, String>> roleNameList = (List<LinkedHashMap<String, String>>) claims.get("roles");

            List<GrantedAuthority> auths = new ArrayList<>();

            for (LinkedHashMap<String, String> map : roleNameList) {
                for (String role : map.values()) {
                    auths.add(new SimpleGrantedAuthority(role));
                }
            }
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, auths) :
                    null;
        }
        return null;
    }
}

