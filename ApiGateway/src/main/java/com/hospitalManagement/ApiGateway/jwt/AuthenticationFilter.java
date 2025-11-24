package com.hospitalManagement.ApiGateway.jwt;
import com.hospitalManagement.ApiGateway.router.RouteValidator;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // ✅ Skip authentication for open APIs
            if (!validator.isSecured.test(request)) {
                return chain.filter(exchange);
            }

            // ✅ Check JWT header
            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            log.info("token :"+authHeader);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Missing or invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);
            log.info("Token :"+token);


            if (!jwtUtils.validateToken(token)) {
                return onError(exchange, "Invalid or expired JWT", HttpStatus.UNAUTHORIZED);
            }

            // ✅ Extract user info from token
            Claims claims = jwtUtils.getClaims(token);
            String username = claims.getSubject();
            List<String> roles = claims.get("roles", List.class);

            // ✅ Add headers for downstream microservices
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                    .header("X-User", username)
                    .header("X-Roles", String.join(",", roles))
                    .build();
            log.info("mutated request : "+mutatedRequest.getHeaders());

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        System.out.println("JWT Filter Error: " + err);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Add future configs if needed
    }
}