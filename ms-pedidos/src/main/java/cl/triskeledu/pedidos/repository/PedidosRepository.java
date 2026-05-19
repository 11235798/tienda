package cl.triskeledu.pedidos.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.pedidos.dto.PedidosResponse;

public class PedidosRepository {
private List<PedidosResponse> pedidos = new ArrayList<>();

    // Obtener todos los registros
    public List<PedidosResponse> findAll() {
        return pedidos;
    }

    // Buscar por ID usando Streams de Java 8+
    public PedidosResponse findById(Long id) {
        return pedidos.stream()
            .filter(o -> o.getId().equals(id)) // Se usa .equals() para comparar objetos Long
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID del cliente
    public List<PedidosResponse> findByClienteId(Long clienteId) {
        return pedidos.stream()
            .filter(o -> o.getClienteId() != null && o.getClienteId().equals(clienteId))
            .toList();
    }

    // Buscar por ID del producto
    public List<PedidosResponse> findByProductoId(Long productoId) {
        return pedidos.stream()
            .filter(o -> o.getProductoId() != null && o.getProductoId().equals(productoId))
            .toList();
    }

    // Buscar por estado del pedido (ignorando mayúsculas/minúsculas)
    public List<PedidosResponse> findByEstado(String estado) {
        return pedidos.stream()
            .filter(o -> o.getEstado() != null && o.getEstado().equalsIgnoreCase(estado))
            .toList();
    }

    // Guardar o actualizar
    public PedidosResponse save(PedidosResponse item) {

        // CREAR (Se verifica si es null o 0 para mayor seguridad)
        if (item.getId() == null || item.getId() == 0) {
            Long lastId = (long) (pedidos.size() + 1);
            item.setId(lastId);
            pedidos.add(item);
            return item;
        }

        // ACTUALIZAR
        PedidosResponse itemEncontrado = findById(item.getId());

        if (itemEncontrado == null) {
            return null;
        }

        // Se actualizan todos los campos nuevos basados en PedidosResponse
        itemEncontrado.setClienteId(item.getClienteId());
        itemEncontrado.setCodigoSeguimiento(item.getCodigoSeguimiento());
        itemEncontrado.setEstado(item.getEstado());
        itemEncontrado.setNotasEntrega(item.getNotasEntrega());
        itemEncontrado.setFechaCreacion(item.getFechaCreacion());
        itemEncontrado.setProductoId(item.getProductoId());
        itemEncontrado.setCantidad(item.getCantidad());
        itemEncontrado.setPrecioUnitario(item.getPrecioUnitario());
        itemEncontrado.setSubtotal(item.getSubtotal());

        return itemEncontrado;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return pedidos.removeIf(item -> item.getId().equals(id));
    }
}

