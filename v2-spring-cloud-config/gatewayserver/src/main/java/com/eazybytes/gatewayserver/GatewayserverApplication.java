package com.eazybytes.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

@SpringBootApplication
public class GatewayserverApplication {

    private static final String BASE_PATH = "/eazybank";
    private static final String SEGMENT_PATTERN = "(?<segment>.*)";
    private static final String SEGMENT_REPLACEMENT = "/${segment}";
    private static final String RESPONSE_TIME_HEADER = "X-Response-Time";
    private static final String CIRCUIT_BREAKER = "CircuitBreaker";

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

    private Function<PredicateSpec, Buildable<Route>> createServiceRoute(String service) {
        return route -> (Route.AsyncBuilder) route
                .path(BASE_PATH + "/" + service + "/**")
                .filters(f -> f.rewritePath(
                                BASE_PATH + "/" + service + "/" + SEGMENT_PATTERN,
                                SEGMENT_REPLACEMENT)
                        .addResponseHeader(RESPONSE_TIME_HEADER,
                                LocalDateTime.now().toString())
                        .circuitBreaker(config -> config.setName(service + CIRCUIT_BREAKER)
                                .setFallbackUri("forward:/contactSupport"))
                        .retry(retryConfig -> retryConfig.setRetries(3)
                                .setMethods(HttpMethod.GET)
                                .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))
                .uri("lb://" + service.toUpperCase());
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory
                .configureDefault(id -> new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
    }

}
