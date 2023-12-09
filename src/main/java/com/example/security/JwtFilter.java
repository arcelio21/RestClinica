package com.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor //LOS CAMPOS OBLIGATORIOS(Final) LO AGREGA A UN CONSTRUCTOR
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    private final List<String> urlsIgnore = List.of("/swagger-ui.html", "/api/v1/auth", "/swagger-ui", "/swagger-ui", "/v3/api-docs", "/webjars", "/api-docs","/api/v1/village");
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return urlsIgnore.stream().anyMatch(urlIgnore->request.getRequestURI().contains(urlIgnore));
    }



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader= request.getHeader(AUTHORIZATION);
        final String cedula;
        final String jwtToken;

        if(authHeader==null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        jwtToken = authHeader.substring(7);
        cedula =this.jwtUtils.extractUsername(jwtToken);

        if(cedula!=null && SecurityContextHolder.getContext().getAuthentication()==null){ //Valida que la cedula no sea nula y que no haya una autentifacion definida

            UserDetails userDetails = userDetailsService.loadUserByUsername(cedula);
            if (this.jwtUtils.isTokenValid(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); //SE AGREGA LA AUTORIZACION AL CONTEXTO DE SPRING
            }

        }

        filterChain.doFilter(request,response);
    }
}
