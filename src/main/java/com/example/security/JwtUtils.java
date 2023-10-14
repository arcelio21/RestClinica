package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.entity.user.TuserReg;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtUtils {

    private final String jwtSigningKey = "holaarceliomontezumanorielmelendezestasbien";

    /**
     * Extrae informacion de usuario del token
     * @param token
     * @return
     */
    public  String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extra fecha o tiempo de expiracion del token
     * @param token
     * @return
     */
    public Date extractExpiration(String token){
        return  extractClaim(token,Claims::getExpiration);
    }

    public boolean hasClaim(String token, String claimName){
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) !=null;
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Para obtener todos los datos adicionales del token
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyByte= Decoders.BASE64.decode(this.jwtSigningKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    /**
     * Valida si el token esta expirado
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(TuserReg uTuserReg){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,uTuserReg);
    }
    public String generateToken(TuserReg uTuserReg, Map<String,Object> claims){
        return createToken(claims, uTuserReg);
    }

    private String createToken(Map<String, Object> claims, TuserReg uTuserReg) {

        return  Jwts.builder().setClaims(claims)
                .setSubject(uTuserReg.getUsername())
                .claim("authorities",uTuserReg.getAuthorities())
                .claim("fullname", uTuserReg.getName()+" "+uTuserReg.getLastName())
                .claim("fechaNacimiento", uTuserReg.getBirthday().toString())
                .claim("email", uTuserReg.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.HOURS.toMillis(24)))
                .signWith(this.getSignInKey(),SignatureAlgorithm.HS256).compact();
    }

    /**
     * Valida que el usuario del token sea el mismo del userdetails y valida que el token no se haya expirado
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
