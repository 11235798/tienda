package cl.triskeledu.usuarios.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosResenas {
    private Long usuarioId; // Corresponde al ID del Usuario
    private String username;
    private String email;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    // --- Datos de Perfil (Relación 1 a 1) ---
    // No se suele incluir el ID del perfil si ya tienes el del usuario, pero puedes agregarlo si lo necesitas
    private String nombre;
    private String apellido;
    private String telefono;
    private String biografia;

    // --- Datos de Dirección (Relación 1 a N) ---
    // Representará una dirección específica en esa "fila"
    private Long direccionId;
    private String calle;
    private String ciudad;
    private String pais;
    private String codigoPostal;

    // --- Datos de Rol (Relación N a M) ---
    // Representará un rol específico en esa "fila"
    private Long rolId;
    private String nombreRol; // Renombrado para no chocar con el nombre del Perfil si decides agregarlo
}

