package com.vestibulando.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment  env;

    @Autowired
    JwtTokenStore jwtTokenStore;

    private static final String[] PUBLIC = {"/oauth/token"};
    private static final String[] PUBLIC_POST = {"/usuarios"};

    private static final String[] PUBLIC_GET = {"/oauth/token","/h2-console/**","/swagger-ui.html"};
    private static final String[] USUARIO_GET= {"/simulados","/respostasUsuarios","/materia","/banca"};
    private static final String[] USUARIO= {"/respostasUsuarios"};

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }
        http.cors().and().authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.POST,PUBLIC_POST).permitAll()
                .antMatchers(HttpMethod.GET,PUBLIC_GET).permitAll()
                .antMatchers(HttpMethod.GET,USUARIO_GET).hasAnyRole("USER","ADMIN")
                .antMatchers(USUARIO).hasAnyRole("USER","ADMIN")
                .anyRequest().hasRole("ADMIN");
    }
}