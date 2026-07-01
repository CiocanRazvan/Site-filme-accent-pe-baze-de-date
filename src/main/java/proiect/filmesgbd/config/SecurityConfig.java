package proiect.filmesgbd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import proiect.filmesgbd.security.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/login.html",
                                "/static/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/clienti/login",
                                "/api/clienti/inregistrare"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/clienti/email"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/filme/**",
                                "/api/actori/**",
                                "/api/distributie/**",
                                "/api/versiuni/**",
                                "/api/recenzii/**",
                                "/api/statistici/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,  "/api/recenzii/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,   "/api/recenzii/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/recenzii/**").authenticated()
                        .requestMatchers("/api/vizualizari/**").authenticated()
                        .requestMatchers("/api/clienti/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}