package cl.triskeledu.descuentos.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CampanaDesRequest {
    @NotBlank(message = "El código promoción es obligatorio")
    @Size(min = 1, max = 50, message = "El código debe tener entre 1 y 50 caracteres")
    // @Column(name = "codigo_promocion", nullable = false)
    private String codigoPromocion;
    // // codigo_promocion    VARCHAR(50)     UNIQUE NOT NULL,
    @NotBlank(message = "El nombre campaña es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre campaña debe tener entre 1 y 100 caracteres")
    // @Column(name = "nombre_campana", nullable = false)
    private String nombreCampana;
    // // nombre_campana      VARCHAR(100)    NOT NULL,
    @NotBlank(message = "El porcentaje rebaja es obligatorio")
    @Min(value = 1)
    @Max(value = 100)
    // @Column(name = "porcentaje_rebaja", nullable = false)
    private int porcentajeRebaja;
    // // porcentaje_rebaja   INT             NOT NULL CHECK (porcentaje_rebaja > 0 AND porcentaje_rebaja <= 100),
    @NotBlank(message = "La fecha inicio es obligatoria")
    // @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;
    // // fecha_inicio        TIMESTAMP       NOT NULL,
    @NotBlank(message = "La fecha fin es obligatoria")
    // @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;
    // // fecha_fin           TIMESTAMP       NOT NULL,
    // // CHECK (fecha_fin > fecha_inicio)
}
