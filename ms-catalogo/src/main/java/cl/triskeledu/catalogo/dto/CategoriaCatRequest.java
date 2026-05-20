package cl.triskeledu.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaCatRequest {
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    @Size(min = 1, max = 100,
        message = "El nombre de la categoria debe tener entre 1 y 100 caracteres")
    private String nombreCategoria;
}
