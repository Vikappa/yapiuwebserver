package vincenzocostantini.yapiu.yapiuwebserver.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vincenzocostantini.yapiu.yapiuwebserver.entities.users.Utente;
import vincenzocostantini.yapiu.yapiuwebserver.exceptions.InvalidTokenException;
import vincenzocostantini.yapiu.yapiuwebserver.payload.loginDTO.CustomerLoginDTO;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;


@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente user){

        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 8)) // 8 ore di vita
                .subject(String.valueOf(user.getEmail()))
                .claim("role", user.getRuolo())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))) //Firma con algoritmo HMAC
                .compact();
    }

    public String createCustomerToken(CustomerLoginDTO customerLoginDto) {
        return Jwts.builder()
//                .setIssuer("YAPIUWEBAPI") metodo setIssuer deprecato, si inserisce dirattamente il claim con valore issuer
                .claim("iss", "YAPIUWEBAPI")
                .claim("role", "CUSTOMER")
                .claim("tableNumber", customerLoginDto.tavNum())
//                .setIssuedAt(new Date(System.currentTimeMillis())) metodi deprecati e sostituidi dal claim diretto del campo iat (issued at time) end exp
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) metodi deprecati e sostituidi dal claim diretto del campo iat (issued at time) end exp
                .claim("iat", Date.from(Instant.ofEpochSecond(1466796822L)))
                .claim("exp", Date.from(Instant.ofEpochSecond(4622470422L)))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))) //Firma con algoritmo HMAC
                .compact();
    }

    public void verifyToken(String token) throws InvalidTokenException {
        try {
        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parse(token);
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("Expired token", 511);
        } catch (SignatureException | MalformedJwtException e) {
           throw new InvalidTokenException("Invalid token", 512);
        } catch (Exception e) {
            throw new InvalidTokenException("Token verification failed", 513);
        }
    }

    public String extractIdFromToken(String token){
        return Jwts.parser().
                verifyWith(Keys.hmacShaKeyFor((secret.getBytes(StandardCharsets.UTF_8)))).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public int extractTableNumber(String token) {
        String tokenClean = token.startsWith("Bearer ") ? token.substring(7) : token;

        // Costruisci la chiave di firma
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        // Costruisci il parser JWT
        JwtParserBuilder parserBuilder = Jwts.parser().setSigningKey(key);

        // Estrai i claims dal token usando il parser
        Claims claims = parserBuilder.build().parseSignedClaims(tokenClean).getPayload();

        // Ottieni il valore del claim "tableNumber"
        return claims.get("tableNumber", Integer.class);
    }
}