package com.eazybytes.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.function.Function;

@SpringBootApplication
public class GatewayserverApplication {

    private static final String BASE_PATH = "/eazybank";
    private static final String SEGMENT_PATTERN = "(?<segment>.*)";
    private static final String SEGMENT_REPLACEMENT = "/${segment}";
    private static final String RESPONSE_TIME_HEADER = "X-Response-Time";

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator configureGatewayRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(createServiceRoute("accounts"))
                .route(createServiceRoute("loans"))
                .route(createServiceRoute("cards"))
                .build();
    }

    private Function<PredicateSpec, Buildable<Route>> createServiceRoute(
            String service) {
        return r -> (Route.AsyncBuilder) r.path(BASE_PATH + "/" + service + "/**")
                .filters(f -> f.rewritePath(
                                BASE_PATH + "/" + service + "/" + SEGMENT_PATTERN,
                                SEGMENT_REPLACEMENT)
                        .addResponseHeader(RESPONSE_TIME_HEADER,
                                LocalDateTime.now().toString()))
                .uri("lb://" + service.toUpperCase());
    }


}
