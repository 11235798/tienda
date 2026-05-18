package cl.triskeledu.usuarios.dto;


import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public class UsuariosRequest {
    // --- Datos de Cuenta ---
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    // --- Roles ---
    // Solo necesitamos los IDs de los roles para asociarlos al usuario en la BD
    @NotEmpty(message = "Debe asignar al menos un rol al usuario")
    private Set<Long> rolIds;
    @NotBlank(message = "La calle es obligatoria")
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El país es obligatorio")
    private String pais;
    

}
