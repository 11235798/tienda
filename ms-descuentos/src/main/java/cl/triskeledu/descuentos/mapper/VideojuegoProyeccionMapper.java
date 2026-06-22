package cl.triskeledu.descuentos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.descuentos.dto.VideojuegoProyeccionRequest;
import cl.triskeledu.descuentos.dto.VideojuegoProyeccionResponse;
import cl.triskeledu.descuentos.model.VideojuegoProyeccion;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideojuegoProyeccionMapper {
    @Mapping(target = "id", ignore = true)
    VideojuegoProyeccion toEntity(VideojuegoProyeccionRequest request);

    VideojuegoProyeccionResponse toResponse(VideojuegoProyeccion videojuego);

    List<VideojuegoProyeccionResponse> toResponseList(List<VideojuegoProyeccion> videojuegos);

    @Mapping(target = "id", ignore = true)
    void updateEntity(VideojuegoProyeccionRequest request, @MappingTarget VideojuegoProyeccion videojuego);
}
