package cl.triskeledu.usuarios.mapper;

import cl.triskeledu.usuarios.dto.UsuarioRequest;
import cl.triskeledu.usuarios.dto.UsuarioResponse;
import cl.triskeledu.usuarios.model.Usuario;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequest request);

    UsuarioResponse toResponse(Usuario entity);

    List<UsuarioResponse> toResponseList(List<Usuario> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(
            UsuarioRequest request,
            @MappingTarget Usuario entity
    );
}