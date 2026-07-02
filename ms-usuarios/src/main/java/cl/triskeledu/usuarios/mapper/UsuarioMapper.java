package cl.triskeledu.usuarios.mapper;

import cl.triskeledu.usuarios.dto.UsuarioRequest;
import cl.triskeledu.usuarios.dto.UsuarioResponse;
import cl.triskeledu.usuarios.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id",         ignore = true)
    @Mapping(target = "activo",     ignore = true)
    @Mapping(target = "perfil",     ignore = true)
    @Mapping(target = "credencial", ignore = true)
    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(Usuario usuario);

    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);


    @Mapping(target = "id",         ignore = true)
    @Mapping(target = "activo",     ignore = true)
    @Mapping(target = "perfil",     ignore = true)
    @Mapping(target = "credencial", ignore = true)
    void updateEntity(UsuarioRequest request, @MappingTarget Usuario usuario);
}