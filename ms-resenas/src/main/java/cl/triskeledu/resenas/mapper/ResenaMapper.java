package cl.triskeledu.resenas.mapper;

import org.springframework.stereotype.Component;

import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.dto.UsuarioResponse;
import cl.triskeledu.resenas.dto.VideojuegoResponse;
import cl.triskeledu.resenas.model.Resena;

@Component
public class ResenaMapper {

    public ResenaResponse toResponse(
            Resena resena,
            UsuarioResponse usuario,
            VideojuegoResponse videojuego) {

        return ResenaResponse.builder()
                .id(resena.getId())
                .usuarioId(usuario.getId())
                .usuarioNickname(usuario.getNickname())
                .videojuegoId(videojuego.getId())
                .videojuegoTitulo(videojuego.getTitulo())
                .calificacion(resena.getCalificacion())
                .comentario(resena.getComentario())
                .build();
    }

}