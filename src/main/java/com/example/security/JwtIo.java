package com.example.security;

import com.example.util.GsonUtil;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.TimeZone;

@Component
public class JwtIo {

    @Value("${jwt.token.secret:secret}")
    private String SECRET;

    @Value("${jwt.timezone:UTC}")
    private String TIMEZONE;

    @Value("${jwt.token.expiresIn:3600}")
    private int EXPIRES_IN;

    @Value("${jwt.issuer:none}")
    private String ISSUER;

    public String generateToken(Object src){
        String subject = GsonUtil.serializae(src);

        //Construir un HMAC signer usando SHA-256
        Signer signer = HMACSigner.newSHA256Signer(this.SECRET); //Firma utilizando la palabra secreta
        TimeZone tz = TimeZone.getTimeZone(this.TIMEZONE);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(tz.toZoneId()).plusSeconds(this.EXPIRES_IN); //Para agregarle al tiempo actual los segundos de expiracion

        JWT jwt = new JWT().setIssuer(this.ISSUER) //Persona o entidad que esta generando el token
                .setIssuedAt(ZonedDateTime.now(tz.toZoneId())) //Fecha en la que se solicita el token
                .setSubject(subject) // Agregar payload enviado
                .setExpiration(zonedDateTime); //Expiracion
        return  JWT.getEncoder().encode(jwt,signer); // Se genera el token
    }

    /**
     * Devolvara true cuando este expirado
     * @param encodedJWT
     * @return
     */
    public boolean validateToken(String encodedJWT){

        boolean validate = false;

        try {
            JWT jwt = this.jwt(encodedJWT);
            validate= jwt.isExpired();
        }catch (Exception e){
            e.printStackTrace(System.out);
            validate = true;
        }

        return  validate;
    }

    public String getPayload(String encodedJWT){

        JWT jwt = this.jwt(encodedJWT);
        return  jwt.subject;
    }

    private JWT jwt (String encodedJWT){
        Verifier verifier = HMACVerifier.newVerifier(this.SECRET); //Con esto se verifica un JWT tiene la firma del algoritmo HMAC

        return JWT.getDecoder().decode(encodedJWT,verifier);
    }
}
