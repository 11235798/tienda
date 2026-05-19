package cl.triskeledu.notificaciones.dto;

import java.time.LocalDateTime;

import cl.triskeledu.notificaciones.modelo.CanalNotificacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificasionesResponse {
    private Long id;
    private String codigo;
    private String asunto;
    private String contenidoBase;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String destinatario;
    private CanalNotificacion canal;
    private String mensajeFinal;
    private Boolean enviado;
    private Long plantillaId; // Referencia simple a la plantilla utilizada
    private String codigoPlantilla; // Útil para mostrarlo en UI sin buscar el ID
    private LocalDateTime fechaEnvio;


}
