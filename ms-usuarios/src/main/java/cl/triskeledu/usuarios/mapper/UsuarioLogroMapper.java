package cl.triskeledu.usuarios.mapper;

import cl.triskeledu.usuarios.dto.UsuarioLogroResponse;
import cl.triskeledu.usuarios.model.UsuarioLogro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioLogroMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "logroId", source = "logro.id")
    UsuarioLogroResponse toResponse(UsuarioLogro entity);

    List<UsuarioLogroResponse> toResponseList(List<UsuarioLogro> entities);
}