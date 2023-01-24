package com.example.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Esta clase se encarga de agregar los interceptores configurado, mas espcifico el intercepto par jwt
 */
@Component
public class InterceptorJwt implements WebMvcConfigurer {

    @Value("${jwt.security.enabled:false}")
    private boolean securitEnabled;

    private InterceptorJwtIo interceptorJwtIo;

    public InterceptorJwt(InterceptorJwtIo interceptorJwtIo) {
        this.interceptorJwtIo = interceptorJwtIo;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(securitEnabled){
            registry.addInterceptor(interceptorJwtIo);
        }
    }
}
