package com.test.springboot02.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("-----------------");
        log.info("useRequest :"+ userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName :"+ clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("-----------------");
        oAuth2User.getAttributes().forEach((i,v)->{
            log.info(i +" : "+ v);
        });


        return oAuth2User;
    }
}
