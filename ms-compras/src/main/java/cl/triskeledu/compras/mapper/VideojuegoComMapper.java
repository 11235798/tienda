package cl.triskeledu.compras.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.compras.dto.VideojuegoComRequest;
import cl.triskeledu.compras.dto.VideojuegoComResponse;
import cl.triskeledu.compras.model.VideojuegoCompras;

@Mapper(componentModel = "spring")
public interface VideojuegoComMapper {
    @Mapping(target = "id", ignore = true)
    VideojuegoCompras toEntity(VideojuegoComRequest request);

    VideojuegoComResponse toResponse(VideojuegoCompras videojuego);

    List<VideojuegoComResponse> toResponseList(List<VideojuegoCompras> videojuegos);

    @Mapping(target = "id", ignore = true)
    void updateEntity(VideojuegoComRequest request, @MappingTarget VideojuegoCompras videojuego);
}
