package cl.triskeledu.compras.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VideojuegoComRequest {
    @NotBlank(message = "El sku es obligatorio")
    @Size(min = 1, max = 20, message = "El sku debe tener entre 1 y 20 caracteres")
    private String sku;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    private String titulo;

    @NotBlank(message = "El formato es obligatorio")
    @Size(min = 1, max = 50, message = "El formato debe tener entre 1 y 50 caracteres")
    @Pattern(
        regexp = "Físico|Digital",
        message = "El formato debe ser: 'Físico' o 'Digital'"
    )
    private String formato;

    @NotBlank(message = "El precio actual es obligtorio")
    @Min(value = 0, message = "El precio actual no puede ser menor a 0")
    private int precioActual;
}
