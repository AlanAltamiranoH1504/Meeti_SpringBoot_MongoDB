package altamirano.hernandez.meeti_springboot_mongodb.jwt;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    @Value("${JWT.SECRET_KEY}")
    String JWT_SECRET_KEY;

    //Generacion de token con claims
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    //Generador de token
    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 10000 * 6000 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
    }

    //Firma del token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Obtener email del token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //Obtiene fecha de expiracion de token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Obtiene claims del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Obtiene all claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Verificacion de token expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Valida el token
    public boolean validateToken(String token, Usuario usuario) {
        final String email = extractEmail(token);
        return (email.equals(usuario.getEmail()) && !isTokenExpired(token));
    }
}
