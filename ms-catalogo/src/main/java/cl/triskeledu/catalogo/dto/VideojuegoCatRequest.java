package cl.triskeledu.catalogo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VideojuegoCatRequest {
    @NotBlank(message = "El sku del videojuego es obligatorio")
    @Size(min = 1, max = 20,
        message = "El sku debe tener entre 1 y 20 caracteres")
    private String skuVid;

    @NotBlank(message = "El titulo del videojuego es obligatorio")
    @Size(min = 1, max = 255,
        message = "El título debe tener entre 1 y 255 caracteres")
    private String tituloVid;

    @NotBlank(message = "La desarrolladora del videojuego es obligatorio")
    @Size(min = 1, max = 100,
        message = "La desarrolladora debe tener entre 1 y 255 caracteres")
    private String desarrolladoraVid;

    @NotBlank(message = "El año de lanzamiento del videojuego es obligatorio")
    @Min(value = 1950, message = "El año no puede ser anterior a 1950")
    @Max(value = 2100, message = "El año debe ser anterior o igual a 2100")
    private int anioLanzamientoVid;

    @NotBlank(message = "La plataforma del videojuego es obligatorio")
    @Size(min = 1, max = 150,
        message = "La plataforma debe tener entre 1 y 150 caracteres")
    private String plataformaVid;
}
