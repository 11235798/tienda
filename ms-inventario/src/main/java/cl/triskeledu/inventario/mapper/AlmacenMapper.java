package cl.triskeledu.inventario.mapper;


import cl.triskeledu.inventario.dto.AlmacenResponse;
import cl.triskeledu.inventario.model.Almacen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlmacenMapper{

    AlmacenResponse toResponse(Almacen almacen);

    List<AlmacenResponse> toResponseList(List<Almacen> almacenes);

    // Ignoramos id, productos y fechas al crear para que la BD/JPA los genere
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @ Mapping(target = "nombre", source = "nombre")
    Almacen toEntity(AlmacenResponse request);

    // Actualiza una entidad existente con los datos del request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "nombre", source = "nombre")
    void updateEntityFromRequest(AlmacenResponse request, @MappingTarget Almacen almacen);
}


