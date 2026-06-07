package cl.triskeledu.compras.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.compras.dto.DetalleComRequest;
import cl.triskeledu.compras.dto.DetalleComResponse;
import cl.triskeledu.compras.model.DetalleCompras;

@Mapper(componentModel = "spring")
public interface DetalleComMapper {
    @Mapping(target = "id",             ignore = true)
    @Mapping(target = "fechaCompra",    ignore = true)
    @Mapping(target = "clienteId",      ignore = true)
    @Mapping(target = "videojuegoSku",  ignore = true)
    DetalleCompras toEntity(DetalleComRequest request);

    DetalleComResponse toResponse(DetalleCompras detalle);

    List<DetalleComResponse> toResponseList(List<DetalleCompras> detalles);

    @Mapping(target = "id",             ignore = true)
    @Mapping(target = "fechaCompra",    ignore = true)
    @Mapping(target = "clienteId",      ignore = true)
    @Mapping(target = "videojuegoSku",  ignore = true)
    void updateEntity(DetalleComRequest request, @MappingTarget DetalleCompras detalle);
}
