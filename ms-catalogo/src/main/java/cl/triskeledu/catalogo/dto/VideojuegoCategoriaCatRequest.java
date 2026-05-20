package cl.triskeledu.catalogo.dto;

import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import cl.triskeledu.catalogo.model.VideojuegoCatalogo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VideojuegoCategoriaCatRequest {
    @NotBlank(message = "El ID del videojuego es obligatorio")
    private VideojuegoCatalogo videojuegoVC;

    @NotBlank(message = "El ID de la categoria es obligatorio")
    private CategoriaCatalogo categoriaVC;
}
