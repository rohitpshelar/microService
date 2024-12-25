package com.example.user.service.config;

import com.example.user.service.config.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    @Autowired
    private  ClientRegistrationRepository registrationRepository;

    @Autowired
    private OAuth2AuthorizedClientRepository clientRepository;

    @Bean
    @LoadBalanced
    public RestTemplate restTeamplate(){
        var restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor(manager(registrationRepository, clientRepository)));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    // bean of oAuth2
    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository registrationRepository,
            OAuth2AuthorizedClientRepository clientRepository
    ){
        var provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        var oAuth2AuthorizedClientManager =  new DefaultOAuth2AuthorizedClientManager(registrationRepository,clientRepository);
        oAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
        return oAuth2AuthorizedClientManager;
    }
}
