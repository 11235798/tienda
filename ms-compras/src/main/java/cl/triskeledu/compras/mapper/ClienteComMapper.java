package cl.triskeledu.compras.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import cl.triskeledu.compras.dto.ClienteComRequest;
import cl.triskeledu.compras.dto.ClienteComResponse;
import cl.triskeledu.compras.model.ClienteCompras;

@Mapper(componentModel = "spring")
public interface ClienteComMapper {
    @Mapping(target = "id", ignore = true)
    ClienteCompras toEntity(ClienteComRequest request);

    ClienteComResponse toResponse(ClienteCompras cliente);

    List<ClienteComResponse> toResponseList(List<ClienteCompras> clientes);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ClienteComRequest request, @MappingTarget ClienteCompras cliente);
}
