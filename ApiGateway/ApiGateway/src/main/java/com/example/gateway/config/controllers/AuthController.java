package com.example.gateway.config.controllers;

import com.example.gateway.models.AuthResonse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
//    http://localhost:8084/auth/login
//    rohitpmovies@gmail.com
//    Welcome@123
    @GetMapping("/login")
    public ResponseEntity<AuthResonse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient oAuth2AuthorizedClient,
            @AuthenticationPrincipal OidcUser user,
            Model model
            ){
            logger.info("user email id : {}" ,user.getEmail());

            AuthResonse authResonse = new AuthResonse();
            authResonse.setUserId(user.getEmail());

            authResonse.setAccessToken(oAuth2AuthorizedClient.getAccessToken().getTokenValue());
        authResonse.setRefreshToken(oAuth2AuthorizedClient.getRefreshToken().getTokenValue());
//        authResonse.setExpireAt(oAuth2AuthorizedClient.getRefreshToken().getExpiresAt().getEpochSecond());

        var authorities = user.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).collect(Collectors.toList());
        authResonse.setAuthorities(authorities);
        return new ResponseEntity<>(authResonse, HttpStatus.OK);
    }
}
