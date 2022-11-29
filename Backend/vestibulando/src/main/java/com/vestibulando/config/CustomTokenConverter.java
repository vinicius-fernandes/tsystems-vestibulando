package com.vestibulando.config;

import com.vestibulando.entities.Usuario;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication authentication) {
        if(authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
            Usuario user = (Usuario) authentication.getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<String, Object>();

            additionalInfo.put("userId", user.getId());
            additionalInfo.put("userFullName",user.getNome());

            ((DefaultOAuth2AccessToken) accessToken)
                    .setAdditionalInformation(additionalInfo);
        }
        accessToken = super.enhance(accessToken, authentication);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
        return accessToken;
    }
}
