package cl.triskeledu.inventario.mapper;
import cl.triskeledu.inventario.dto.ProductoInventarioRequest;
import cl.triskeledu.inventario.dto.ProductoInventrarioResponse;
import cl.triskeledu.inventario.model.ProductoInventario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoInventarioMapper {
// Mapeamos la relación hacia el ID en el response
    @Mapping(source = "almacen.id", target = "almacenId")
    ProductoInventrarioResponse toResponse(ProductoInventario entity);

    List<ProductoInventrarioResponse> toResponseList(List<ProductoInventario> entities);

    // Ignoramos el almacén (se setea en el servicio) y campos autogenerados
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "almacen", ignore = true)
    @Mapping(target = "movimientos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    ProductoInventario toEntity(ProductoInventarioRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "almacen", ignore = true)
    @Mapping(target = "movimientos", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    void updateEntityFromRequest(ProductoInventarioRequest request, @MappingTarget ProductoInventario entity);
}
