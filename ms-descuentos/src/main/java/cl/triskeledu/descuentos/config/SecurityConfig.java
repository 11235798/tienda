package cl.triskeledu.descuentos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.triskeledu.common.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/**
 * Configuración de Spring Security para ms-recursos.
 *
 * Este microservicio actúa como RESOURCE SERVER:
 * - NO emite tokens JWT (eso lo hace ms-usuarios)
 * - Valida tokens JWT en cada petición usando el filtro compartido
 * - Aplica reglas de autorización según el rol del usuario
 *
 * Matriz de autorización:
 * ┌──────────────────────────────────┬────────┬────────────────────────────────────────────────┐
 * │ Endpoint                         │ Método │ Acceso                                         │
 * ├──────────────────────────────────┼────────┼────────────────────────────────────────────────┤
 * │ /actuator/**                     │ ALL    │ Público (monitoreo)                            │
 * │ /api/v1/videojuego-descuento/**  │ GET    │ Administrador, Bibliotecario, Cliente          │
 * │ /api/v1/videojuego-descuento/**  │ POST   │ Administrador, Bibliotecario                   │
 * │ /api/v1/videojuego-descuento/**  │ PUT    │ Administrador, Bibliotecario                   │
 * │ /api/v1/videojuego-descuento/**  │ DELETE │ Administrador, Bibliotecario                   │
 * │ /api/v1/campana-descuento/**  │ GET    │ Administrador, Bibliotecario, Cliente          │
 * │ /api/v1/campana-descuento/**  │ POST   │ Administrador, Bibliotecario                   │
 * │ /api/v1/campana-descuento/**  │ PUT    │ Administrador, Bibliotecario                   │
 * │ /api/v1/campana-descuento/**  │ DELETE │ Administrador, Bibliotecario                   │
 * │ /api/v1/videojuego-proyeccion/** │ GET    │ Administrador, Bibliotecario, Cliente          │
 * │ Cualquier otro                   │ ALL    │ Autenticado (con token válido)                 │
 * └──────────────────────────────────┴────────┴────────────────────────────────────────────────┘
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // Actuator siempre público
                .requestMatchers("/actuator/**").permitAll()

                // Lectura de recursos y proyecciones: cualquier rol autenticado
                .requestMatchers(HttpMethod.GET, "/api/v1/videojuego-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor", "Cliente")
                .requestMatchers(HttpMethod.GET, "/api/v1/libros-proyeccion/**")
                    .hasAnyRole("Administrador", "Vendedor", "Cliente")

                // Escritura de recursos: solo Administrador y Vendedor
                .requestMatchers(HttpMethod.POST, "/api/v1/videojuego-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.PUT, "/api/v1/videojuego-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/videojuego-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor")

                // Lectura de recursos y proyecciones: cualquier rol autenticado
                .requestMatchers(HttpMethod.GET, "/api/v1/videojuego-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor", "Cliente")

                // Escritura de recursos: solo Administrador y Vendedor
                .requestMatchers(HttpMethod.POST, "/api/v1/campana-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.PUT, "/api/v1/campana-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/campana-descuento/**")
                    .hasAnyRole("Administrador", "Vendedor")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
