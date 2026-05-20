package cl.triskeledu.catalogo.dto;

import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import cl.triskeledu.catalogo.model.VideojuegoCatalogo;
import lombok.Data;

@Data
public class VideojuegoCategoriaCatResponse {
    private Long idVC;
    private VideojuegoCatalogo videojuegoVC;
    private CategoriaCatalogo categoriaVC;
}
