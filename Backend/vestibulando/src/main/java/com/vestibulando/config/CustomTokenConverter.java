package com.vestibulando.config;

import com.vestibulando.entities.Usuario;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenConverter implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("userFullName", user.getNome());
        map.put("userId",user.getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        System.out.println(accessToken);
        return accessToken;
    }


}
