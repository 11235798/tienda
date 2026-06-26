package cl.triskeledu.recomendaciones.mapper;

import cl.triskeledu.recomendaciones.dto.UsuarioRequest;
import cl.triskeledu.recomendaciones.dto.UsuarioResponse;
import cl.triskeledu.recomendaciones.model.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario toEntity(UsuarioRequest request) {
        return Usuario.builder()
                .nickname(request.getNickname())
                .perfilJugador(request.getPerfilJugador())
                .build();
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nickname(usuario.getNickname())
                .perfilJugador(usuario.getPerfilJugador())
                .build();
    }

}