package com.example.config;

import com.example.mapper.user.MapperUserReg;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        /*httpSecurity.authorizeHttpRequests() //SE NECESITARA AUTORIACION PARA LAS PETICIONES
                .anyRequest() //CUALQUIER PETICION
                .authenticated() //HAY QU AUNTENTICARSE
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
                //.and() //RETORNA UNA INSTANCIA DE HHTTPSECURITY PARA MPODER EJECUTAR OTROS METODOS
                //.httpBasic() ;//FORMA DE REGISTRAR USUARIO BASICO, SERIA UN DIALOG DE JS*/
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**"))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(this.authenticationProvider)
                .addFilterBefore(this.jwtFilter,UsernamePasswordAuthenticationFilter.class);


        return  httpSecurity.build();
    }




}
