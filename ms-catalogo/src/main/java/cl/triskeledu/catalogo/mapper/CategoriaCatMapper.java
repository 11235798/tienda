package cl.triskeledu.catalogo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.catalogo.dto.CategoriaCatRequest;
import cl.triskeledu.catalogo.dto.CategoriaCatResponse;
import cl.triskeledu.catalogo.model.CategoriaCatalogo;

@Mapper(componentModel = "spring")
public interface CategoriaCatMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "videojuegos", ignore = true)
    CategoriaCatalogo toEntity(CategoriaCatRequest request);

    CategoriaCatResponse toResponse(CategoriaCatalogo categoria);

    List<CategoriaCatResponse> toResponseList
    (List<CategoriaCatalogo> categorias);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "videojuegos", ignore = true)
    void updateEntity(CategoriaCatRequest request,
        @MappingTarget CategoriaCatalogo categoriaCatalogo);
}
