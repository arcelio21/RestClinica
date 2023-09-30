package com.example.security;

import com.example.dto.user.typeuser_module.ModuleRoute;
import com.example.service.user.ServiceTypeUserModuleImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private final ServiceTypeUserModuleImpl serviceTypeUserModule;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests((request) -> {
                    this.getRoute(request)
                            .requestMatchers(
                                new AntPathRequestMatcher("/api/v1/province/**",HttpMethod.GET.name()),
                                new AntPathRequestMatcher("/api/v1/village/**",HttpMethod.GET.name()),
                                new AntPathRequestMatcher("/api/v1/district/**",HttpMethod.GET.name()),
                                new AntPathRequestMatcher("/api/v1/address/**", HttpMethod.GET.name()),
                                new AntPathRequestMatcher("/swagger-ui.html"),
                                new AntPathRequestMatcher("/api/v1/auth/**"),
                                new AntPathRequestMatcher("/swagger-ui/index.html"),
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                new AntPathRequestMatcher("/webjars/**"),
                                new AntPathRequestMatcher("/api-docs/**"))
                        .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .sessionManagement((sessionManagementConfigurer)->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)
                )
                .authenticationProvider(this.authenticationProvider)
                .addFilterBefore(this.jwtFilter,UsernamePasswordAuthenticationFilter.class);
        return  httpSecurity.build();
    }


    private AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry getRoute(
            AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry request
    ){

        List<ModuleRoute> routes = this.serviceTypeUserModule.getRouteModule();

        for (ModuleRoute route : routes) {
            request.requestMatchers(
                    new AntPathRequestMatcher(route.getModule(), route.getPrivilege())
            ).hasAuthority(route.getTypeUser());
        }

        return request;
    }


}
