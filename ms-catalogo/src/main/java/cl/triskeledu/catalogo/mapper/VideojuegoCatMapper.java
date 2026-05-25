package cl.triskeledu.catalogo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.catalogo.dto.VideojuegoCatRequest;
import cl.triskeledu.catalogo.dto.VideojuegoCatResponse;
import cl.triskeledu.catalogo.model.VideojuegoCatalogo;

@Mapper(componentModel = "spring")
public interface VideojuegoCatMapper {
    @Mapping(target = "idVid", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    VideojuegoCatalogo toEntity(VideojuegoCatRequest request);

    VideojuegoCatResponse toResponse(VideojuegoCatalogo videojuego);

    List<VideojuegoCatResponse> toResponseList
        (List<VideojuegoCatalogo> videojuegos);
    
    @Mapping(target = "idVid", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    void updateEntity(VideojuegoCatRequest request,
        @MappingTarget VideojuegoCatalogo videojuegoCatalogo);
}
