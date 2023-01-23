package com.example.gatewayservice.config;


import com.example.gatewayservice.filter.AdminFilter;
import com.example.gatewayservice.filter.AppFilter;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;

@Configuration
@RequiredArgsConstructor
public class GatewayConfiguration {
    private final AppFilter filter;
    private final AdminFilter adminFilter;

    private final String endPoint8081 = "http://localhost:8081/";

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/lessons/**")
                        .and().method("POST", "PUT", "DELETE")
                        .filters(f -> f.filter(filter).filter(adminFilter))
                        .uri(endPoint8081))
                .route(r -> r.path("/api/v1/lessons/**")
                        .and().method("GET")
                        .filters(f -> f.filter(filter))
                        .uri(endPoint8081))
                .route(r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8084/"))
                .route(r -> r.path("/api/v1/userCerts/**")
                        .and().method("GET")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8084/"))
                .route(r -> r.path("/api/v1/auth/all-users")
                        .filters(f -> f.filter(filter).filter(adminFilter))
                        .uri("http://localhost:8085/"))
                .route(r -> r.path("/api/v1/auth/**")
                        .uri("http://localhost:8085/"))
                .route(r -> r.path("/api/v1/lessons/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:9090/"))
                .build();
    }
}
