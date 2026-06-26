package cl.triskeledu.recomendaciones.mapper;

import cl.triskeledu.recomendaciones.dto.RecomendacionResponse;
import cl.triskeledu.recomendaciones.dto.UsuarioResponse;
import cl.triskeledu.recomendaciones.dto.VideojuegoResponse;
import cl.triskeledu.recomendaciones.model.Recomendacion;

public class RecomendacionMapper {

    private RecomendacionMapper() {
    }

    public static RecomendacionResponse toResponse(
        Recomendacion recomendacion,
        UsuarioResponse usuario,
        VideojuegoResponse videojuego) {

    return RecomendacionResponse.builder()
            .id(recomendacion.getId())
            .usuarioId(usuario.getId())
            .nicknameUsuario(usuario.getNickname())
            .videojuegoId(videojuego.getId())
            .tituloVideojuego(videojuego.getTitulo())
            .porcentajeAfinidad(recomendacion.getPorcentajeAfinidad())
            .estado(recomendacion.getEstado())
            .build();
}

}