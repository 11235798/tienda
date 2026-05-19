package cl.triskeledu.inventario.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.inventario.dto.InventarioResponse;

public class InventarioRepository {
private List<InventarioResponse> inventarios = new ArrayList<>();

    // Obtener todos los registros
    public List<InventarioResponse> findAll() {
        return inventarios;
    }

    // Buscar por ID usando Streams de Java 8+
    public InventarioResponse findById(Long id) {
        return inventarios.stream()
            .filter(o -> o.getId().equals(id)) // Se usa .equals() para comparar objetos Long
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID de producto
    public List<InventarioResponse> findByProductoId(Long productoId) {
        return inventarios.stream()
            .filter(o -> o.getProductoId().equals(productoId))
            .toList();
    }

    // Buscar por ID de almacén (Reemplazo lógico para ordenCompraId según tus nuevos campos)
    public List<InventarioResponse> findByAlmacenId(Long almacenId) {
        return inventarios.stream()
            .filter(o -> o.getAlmacenId().equals(almacenId))
            .toList();
    }

    // Guardar o actualizar
    public InventarioResponse save(InventarioResponse item) {

        // CREAR (Se verifica si es null o 0 para mayor seguridad)
        if (item.getId() == null || item.getId() == 0) {
            Long lastId = (long) (inventarios.size() + 1);
            item.setId(lastId);
            inventarios.add(item);
            return item;
        }

        // ACTUALIZAR
        InventarioResponse itemEncontrado = findById(item.getId());

        if (itemEncontrado == null) {
            return null;
        }

        // Se actualizan todos los campos nuevos
        itemEncontrado.setNombre(item.getNombre());
        itemEncontrado.setUbicacion(item.getUbicacion());
        itemEncontrado.setFechaModificacion(item.getFechaModificacion());
        itemEncontrado.setProductoId(item.getProductoId());
        itemEncontrado.setStockActual(item.getStockActual());
        itemEncontrado.setStockMinimo(item.getStockMinimo());
        itemEncontrado.setNombreAlmacen(item.getNombreAlmacen());
        itemEncontrado.setAlmacenId(item.getAlmacenId());
        itemEncontrado.setFechaCreacion(item.getFechaCreacion());
        itemEncontrado.setProductoInventarioId(item.getProductoInventarioId());
        itemEncontrado.setCantidad(item.getCantidad());
        itemEncontrado.setTipoMovimiento(item.getTipoMovimiento());
        itemEncontrado.setObservaciones(item.getObservaciones());
        itemEncontrado.setFechaMovimiento(item.getFechaMovimiento());

        return itemEncontrado;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return inventarios.removeIf(item -> item.getId().equals(id));
    }
}
