package cl.triskeledu.descuentos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class VideojuegoDesRequest {
    @NotBlank(message = "El videojuego id es obligatorio")
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", nullable = false)
    private Long videojuegoId;
    // // videojuego_id   INT         NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    @NotBlank(message = "El campaña id es obligatorio")
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", nullable = false)
    private Long campanaId;
    // // campana_id      INT         NOT NULL REFERENCES campanas_descuento(id) ON DELETE CASCADE,
    @Pattern(
        regexp = "Activo|Pausado",
        message = "El estado debe ser: Activo, Pausado"
    )
    // @Column(name = "estado")
    private String estado;
    // // estado          VARCHAR(20) DEFAULT 'Activo' CHECK (estado IN ('Activo', 'Pausado')),
    // // UNIQUE (videojuego_id, campana_id)
}
