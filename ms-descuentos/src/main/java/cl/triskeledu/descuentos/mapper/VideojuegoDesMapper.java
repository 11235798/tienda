package cl.triskeledu.descuentos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.descuentos.dto.VideojuegoDesRequest;
import cl.triskeledu.descuentos.dto.VideojuegoDesResponse;
import cl.triskeledu.descuentos.model.VideojuegoDescuento;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideojuegoDesMapper {
    @Mapping(target = "id",             ignore = true)
    @Mapping(target = "videojuegoSku",  ignore = true)
    @Mapping(target = "campanaId",      ignore = true)
    VideojuegoDescuento toEntity(VideojuegoDesRequest request);

    @Mapping(target = "videojuegoSku",   ignore = true)
    @Mapping(target = "campanaId",      ignore = true)
    VideojuegoDesResponse toResponse(VideojuegoDescuento videojuegoDes);

    List<VideojuegoDesResponse> toResponseList(List<VideojuegoDescuento> videojuegosDes);

    @Mapping(target = "id",             ignore = true)
    @Mapping(target = "videojuegoSku",   ignore = true)
    @Mapping(target = "campanaId",      ignore = true)
    void updateEntity(VideojuegoDesRequest request, @MappingTarget VideojuegoDescuento videojuegoDes);
}
