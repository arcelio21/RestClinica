package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests() //SE NECESITARA AUTORIACION PARA LAS PETICIONES
                .anyRequest() //CUALQUIER PETICION
                .authenticated() //HAY QU AUNTENTICARSE
                .and() //RETORNA UNA INSTANCIA DE HHTTPSECURITY PARA MPODER EJECUTAR OTROS METODOS
                .httpBasic() ;//FORMA DE REGISTRAR USUARIO BASICO, SERIA UN DIALOG DE JS

        return  httpSecurity.build();
    }
}
