package cl.triskeledu.usuarios.mapper;

import cl.triskeledu.usuarios.dto.LogroRequest;
import cl.triskeledu.usuarios.dto.LogroResponse;
import cl.triskeledu.usuarios.model.Logro;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogroMapper {

    Logro toEntity(LogroRequest request);

    LogroResponse toResponse(Logro entity);

    List<LogroResponse> toResponseList(List<Logro> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(
            LogroRequest request,
            @MappingTarget Logro entity
    );
}