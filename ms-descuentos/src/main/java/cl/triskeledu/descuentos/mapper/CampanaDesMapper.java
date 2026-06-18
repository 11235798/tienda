package cl.triskeledu.descuentos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.descuentos.dto.CampanaDesRequest;
import cl.triskeledu.descuentos.dto.CampanaDesResponse;
import cl.triskeledu.descuentos.model.CampanaDescuento;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CampanaDesMapper {
    @Mapping(target = "id", ignore = true)
    CampanaDescuento toEntity(CampanaDesRequest request);

    CampanaDesResponse toResponse(CampanaDescuento campana);

    List<CampanaDesResponse> toResponseList(List<CampanaDescuento> campanas);

    @Mapping(target = "id", ignore = true)
    void updateEntity(CampanaDesRequest request, @MappingTarget CampanaDescuento campana);
}
