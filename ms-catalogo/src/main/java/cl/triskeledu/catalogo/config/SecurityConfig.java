package cl.triskeledu.catalogo.config;

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
 * Configuración de Spring Security para ms-catalogo.
 *
 * Este microservicio actúa como RESOURCE SERVER:
 * - NO emite tokens JWT (eso lo hace ms-usuarios)
 * - Valida tokens JWT en cada petición usando el filtro compartido
 * - Aplica reglas de autorización según el rol del usuario
 *
 * Matriz de autorización:
 * ┌────────────────────────────┬────────┬──────────────────────────────────┐
 * │ Endpoint                   │ Método │ Acceso                           |
 * ├────────────────────────────┼────────┼──────────────────────────────────┤
 * │ /actuator/**               │ ALL    │ Público (monitoreo)              │
 * │ /api/v1/videojuegos/**     │ GET    │ ADMIN, USER                      │
 * │ /api/v1/videojuegos/**     │ POST   │ ADMIN                            │
 * │ /api/v1/videojuegos/**     │ PUT    │ ADMIN                            │
 * │ /api/v1/videojuegos/**     │ DELETE │ ADMIN                            │
 * │ Cualquier otro             │ ALL    │ Autenticado (con token válido)   │
 * └────────────────────────────┴────────┴──────────────────────────────────┘
 * ┌────────────────────────────┬────────┬──────────────────────────────────┐
 * │ Endpoint                   │ Método │ Acceso                           |
 * ├────────────────────────────┼────────┼──────────────────────────────────┤
 * │ /actuator/**               │ ALL    │ Público (monitoreo)              │
 * │ /api/v1/categorias/**      │ GET    │ ADMIN, USER                      │
 * │ /api/v1/categorias/**      │ POST   │ ADMIN                            │
 * │ /api/v1/categorias/**      │ PUT    │ ADMIN                            │
 * │ /api/v1/categorias/**      │ DELETE │ ADMIN                            │
 * │ Cualquier otro             │ ALL    │ Autenticado (con token válido)   │
 * └────────────────────────────┴────────┴──────────────────────────────────┘
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

                // Lectura de videojuegos: cualquier rol autenticado
                .requestMatchers(HttpMethod.GET, "/api/v1/videojuegos/**")
                    .hasAnyRole("Administrador", "Vendedor", "Cliente")

                // Escritura de videojuegos: solo ADMIN
                .requestMatchers(HttpMethod.POST, "/api/v1/videojuegos/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.PUT, "/api/v1/videojuegos/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/videojuegos/**")
                    .hasAnyRole("Administrador", "Vendedor")

                // Lectura de categorias: cualquier rol autenticado
                .requestMatchers(HttpMethod.GET, "/api/v1/categorias/**")
                    .hasAnyRole("Administrador", "Vendedor", "Cliente")

                // Escritura de videojuegos: solo ADMIN
                .requestMatchers(HttpMethod.POST, "/api/v1/categorias/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.PUT, "/api/v1/categorias/**")
                    .hasAnyRole("Administrador", "Vendedor")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/categorias/**")
                    .hasAnyRole("Administrador", "Vendedor")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
