package tk.scaks.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//                .cors(Customizer.withDefaults())
                .headers(headerSpec -> headerSpec
                        .frameOptions(frameOptionsSpec -> frameOptionsSpec
                                .mode(XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN)
                        )
                )
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/actuator", "/actuator/**", "/", "/logout.html", "/login/**").permitAll()
                        .pathMatchers("/login").permitAll()
                        .pathMatchers("/oauth2/authorization/**").permitAll()
                        .pathMatchers("/**.js", "/**.html", "/**.css").permitAll()
                        .pathMatchers("/assets/**").permitAll()
                        .pathMatchers("/sockjs-node**", "/sockjs-node/**").permitAll()
                        .pathMatchers("/auth**", "/auth/**").permitAll()
                        .pathMatchers("/favicon.ico").permitAll()
                        // Permit all frontend pages
                        .pathMatchers("/foos").permitAll()
                )
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(Customizer.withDefaults())
                )
                .authorizeExchange(exchange -> exchange
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable);

        return http.build();
    }
}