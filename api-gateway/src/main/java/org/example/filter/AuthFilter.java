package org.example.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private RestTemplate restTemplate;

    public AuthFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())){
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing authorization header!");
                }

                String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeaders != null && authHeaders.startsWith("Bearer ")){
                    authHeaders = authHeaders.substring(7);
                }
                try{
                    log.info("Sent REST call to auth service");
                    ResponseEntity<?> response = restTemplate.getForEntity("http://localhost:8080/auth/validate?token=" + authHeaders, ResponseEntity.class);
                    if (response.getStatusCode().is2xxSuccessful()){
                        log.info("REST call to auth service done!");
                    }
                } catch (Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {
    }
}
