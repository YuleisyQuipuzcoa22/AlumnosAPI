package example.com.security;

import io.jsonwebtoken.*;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Clave usada para firmar el token (firma = Signature)
    private static final String SECRET_KEY = "MiSuperClaveSecreta123456789ParaJWT";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    // Convierte la clave -> objeto key compatible con JWT
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generarToken(String email) {
        return Jwts.builder()
                // PAYLOAD (datos del token)
                .setSubject(email) // "sub" -> identificador principal
                .setIssuedAt(new Date()) // "iat" → fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) //"exp" → fecha de expiración
                // HEADER + SIGNATURE (tipo de token y firma)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // HS256 firma 
                .compact(); // Unir todo 
    }

    public String obtenerEmailDeToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
