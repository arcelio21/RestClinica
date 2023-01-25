package com.example.service.jwt;

import com.example.dto.JwtResponse;
import com.example.entity.user.TuserReg;
import com.example.exception.ApiUnauthorized;
import com.example.mapper.user.MapperUserReg;
import com.example.security.JwtIo;
import com.example.util.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private JwtIo jwtIo;
    private DateUtils dateUtils;

    private MapperUserReg mapperUserReg;

    @Value("${jwt.token.expires_in}")
    private int EXPIRES_IN;

    public JwtService(JwtIo jwtIo, MapperUserReg mapperUserReg, DateUtils dateUtils) {
        this.jwtIo = jwtIo;
        this.mapperUserReg = mapperUserReg;
        this.dateUtils= dateUtils;
    }

    public JwtResponse login(String cliendId, String clientSecret) throws ApiUnauthorized {

        TuserReg tuserReg = new TuserReg();
        tuserReg.setIdenCard(Long.valueOf(cliendId));
        tuserReg.setPassword(clientSecret);

        tuserReg = this.mapperUserReg.validateAccount(tuserReg);

        if(tuserReg==null){
            throw new ApiUnauthorized("Datos de usuario no vaidos");
        }

        JwtResponse jwtResponse = JwtResponse.builder()
                .tokenType("bearer")
                .accessToken(jwtIo.generateToken(tuserReg))
                .issuedAt(dateUtils.getDateMillis()+"")
                .clientId(cliendId)
                .expireIn(this.EXPIRES_IN)
                .build();

        return jwtResponse;
    }
}
