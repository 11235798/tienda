package cl.triskeledu.resenas.mapper;

import org.springframework.stereotype.Component;

import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.model.Resena;

@Component
public class ResenaMapper {

    public ResenaResponse toResponse(Resena resena) {

        if (resena == null) {
            return null;
        }

        return new ResenaResponse(
                resena.getId(),

                resena.getVideojuego().getId(),
                resena.getVideojuego().getTitulo(),

                resena.getUsuario().getId(),
                resena.getUsuario().getNickname(),

                resena.getCalificacion(),
                resena.getComentario());
    }
}
