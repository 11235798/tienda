package cl.triskeledu.catalogo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.catalogo.dto.VideojuegoCategoriaCatRequest;
import cl.triskeledu.catalogo.dto.VideojuegoCategoriaCatResponse;
import cl.triskeledu.catalogo.model.VideojuegoCategoriaCatalogo;

@Mapper(componentModel = "spring")
public interface VideojuegoCategoriaCatMapper {
    @Mapping(target = "idVC", ignore = true)
    VideojuegoCategoriaCatalogo toEntity
        (VideojuegoCategoriaCatRequest request);
    
    VideojuegoCategoriaCatResponse toResponse
        (VideojuegoCategoriaCatalogo VC);
    
    List<VideojuegoCategoriaCatResponse> toResponseList
        (List<VideojuegoCategoriaCatalogo> VCs);
    
    @Mapping(target = "idVC", ignore = true)
    void updateEntity(VideojuegoCategoriaCatRequest request,
        @MappingTarget VideojuegoCategoriaCatalogo VC);
}
