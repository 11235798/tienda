package cl.triskeledu.resenas.mapper;

import org.springframework.stereotype.Component;

import cl.triskeledu.resenas.dto.UsuarioResponse;
import cl.triskeledu.resenas.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {

        if (usuario == null) {
            return null;
        }

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNickname(),
                usuario.getEmail());
    }
}