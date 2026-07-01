package cl.triskeledu.resenas.mapper;

import org.springframework.stereotype.Component;

import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.model.Resena;

@Component
public class ResenaMapper {

    public ResenaResponse toResponse(Resena resena) {
        return ResenaResponse.builder()
                .id(resena.getId())
                .usuarioId(resena.getUsuario().getId())
                .usuarioNickname(resena.getUsuario().getNickname())
                .videojuegoId(resena.getVideojuego().getId())
                .videojuegoTitulo(resena.getVideojuego().getTitulo())
                .calificacion(resena.getCalificacion())
                .comentario(resena.getComentario())
                .build();
    }
}