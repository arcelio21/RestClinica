package com.example.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class InterceptorJwtIo implements HandlerInterceptor {

    @Value("${jwt.token.auth.path}")
    private String Auth_Path;

    @Value("#{'${jwt.excluded.path}'.split(',')}")
    private List<String> excluded;
    private JwtIo jwtIo;

    public InterceptorJwtIo(JwtIo jwtIo) {
        this.jwtIo = jwtIo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean validate = false;
        String uri = request.getRequestURI();

        if(uri.equals(this.Auth_Path) || this.excluded(uri)){ //Valida que la ruta este permitida para la peticion
            validate = true;
        }

        if(!validate && request.getHeader("Authorization")!=null && !request.getHeader("Authorization").isEmpty()){
            String token = request.getHeader("Authorization").replaceAll("Bearer","");
            validate = !jwtIo.validateToken(token);
        }

        if(!validate){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return validate;
    }


    //Valida si el path al que se hace la peticion esta permitido para acceder sin token
    private boolean excluded(String path){
        boolean result = false;

        for (String exc :
                this.excluded) {
            if (!exc.equals("#") && exc.equals(path)) {
                return true;
            }
        }

        return result;
    }
}
