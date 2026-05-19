package cl.triskeledu.notificaciones.dto;

import cl.triskeledu.notificaciones.modelo.CanalNotificacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NotificasionesRequest {
    @NotBlank(message = "El destinatario es obligatorio")
    @Size(max = 150, message = "El destinatario no puede exceder los 150 caracteres")
    private String destinatario;

    @NotNull(message = "El canal de notificación es obligatorio")
    private CanalNotificacion canal;

    @NotNull(message = "El ID de la plantilla es obligatorio")
    private Long plantillaId;

    @NotBlank(message = "El código de la plantilla es obligatorio")
    @Size(max = 50, message = "El código no puede exceder los 50 caracteres")
    private String codigo;

    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 200, message = "El asunto no puede exceder los 200 caracteres")
    private String asunto;

    @NotBlank(message = "El contenido base es obligatorio")
    private String contenidoBase;
}
