package cl.triskeledu.compras.dto;

public class ClienteComRequest {
    @NotBlank(message = "El rut es obligatorio")
    @Size(min = 1, max = 15, message = "El rut debe tener entre 1 y 15 caracteres")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Size(min = 1, max = 150, message = "El email debe tener entre 1 y 150 caracteres")
    private String email;
}
