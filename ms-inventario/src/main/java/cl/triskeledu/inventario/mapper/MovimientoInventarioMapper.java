package cl.triskeledu.inventario.mapper;

import cl.triskeledu.inventario.dto.MovimientoInventarioRequest;
import cl.triskeledu.inventario.dto.MovimientoInventarioResponse;

import java.util.List;

public interface MovimientoInventarioMapper {
List<MovimientoInventarioResponse> obtenerTodos();
    MovimientoInventarioResponse obtenerPorId(Long id);
    List<MovimientoInventarioResponse> obtenerPorProductoInventario(Long productoInventarioId);
    MovimientoInventarioResponse registrarMovimiento(MovimientoInventarioRequest request);
}
