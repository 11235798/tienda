package cl.triskeledu.resenas.mapper;

import org.springframework.stereotype.Component;

import cl.triskeledu.resenas.dto.VideojuegoResponse;
import cl.triskeledu.resenas.model.Videojuego;

@Component
public class VideojuegoMapper {

    public VideojuegoResponse toResponse(Videojuego videojuego) {

        if (videojuego == null) {
            return null;
        }

        return new VideojuegoResponse(
                videojuego.getId(),
                videojuego.getSku(),
                videojuego.getTitulo(),
                videojuego.getDesarrolladora(),
                videojuego.getAnioLanzamiento());
    }
}
