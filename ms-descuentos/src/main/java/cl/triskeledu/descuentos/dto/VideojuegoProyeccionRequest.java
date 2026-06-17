package cl.triskeledu.descuentos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VideojuegoProyeccionRequest {
    @NotBlank(message = "El sku es obligatorio")
    @Size(min = 1, max = 50, message = "El sku debe tener entre 1 y 50 caracteres")
    // @Column(name = "sku", nullable = false)
    private String sku;
    // // sku         VARCHAR(20)  UNIQUE NOT NULL,
    @NotBlank(message = "El título es obligatorio")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    // @Column(name = "titulo", nullable = false)
    private String titulo;
    // // titulo      VARCHAR(255) NOT NULL,
    @NotBlank(message = "El precio base es obligatorio")
    @Min(value = 0, message = "El precio base debe ser mayor o igual a 0")
    // @Column(name = "precio_base", nullable = false)
    private int precioBase;
    // // precio_base INT          NOT NULL CHECK (precio_base >= 0)
}
