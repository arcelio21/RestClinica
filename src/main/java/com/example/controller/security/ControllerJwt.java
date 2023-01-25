package com.example.controller.security;

import com.example.exception.ApiUnauthorized;
import com.example.service.jwt.JwtService;
import com.example.validator.AuthValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jwt")
@Slf4j
public class ControllerJwt {

    private JwtService jwtService;
    private AuthValidator authValidator;

    public ControllerJwt(JwtService jwtService, AuthValidator authValidator) {
        this.jwtService = jwtService;
        this.authValidator = authValidator;
    }

    // MediaType: ES EL TIPO DE DATO QUE SE UTILIZA PARA HACER PETICIONES AUTH2.0, Y QUE PUEDA FUNCIONAR EN POSTMAN
    @PostMapping(path = "/auth/cliente_credential/AccessToken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> paramMap,
                                        @RequestParam("grant_type") String grantType)  throws ApiUnauthorized{

        log.info("Controlador");
        System.out.println("controlador");
        this.authValidator.validate(paramMap, grantType);
        return ResponseEntity.ok(this.jwtService.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
    }

}
