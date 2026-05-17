package cl.triskeledu.compras.repository;

import cl.triskeledu.compras.dto.ComprasResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository
public class ComprasRepository {
     private List<ComprasResponse> compras = new ArrayList<>();

    // Obtener todos los registros
    public List<ComprasResponse> findAll() {
        return compras;
    }

    // Buscar por ID usando Streams de Java 8+
    public ComprasResponse findById(Long id) {
        return compras.stream()
            .filter(o -> o.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID de producto
    public List<ComprasResponse> findByProductoId(  Long productoId) {
        return compras.stream()
            .filter(o -> o.getProductoId() == productoId)
            .toList();
    }

    // Buscar por ID de orden de compra
    public List<ComprasResponse> findByOrdenCompraId(Long ordenCompraId) {
        return compras.stream()
            .filter(o -> o.getOrdenCompraId() == ordenCompraId)
            .toList();
    }

    // Guardar o actualizar
    public ComprasResponse save(ComprasResponse compra) {

        // CREAR
        if (compra.getId() == 0) {
            Long lastId = (long) (compras.size() + 1);
            compra.setId(lastId);
            compras.add(compra);
            return compra;
        }

        // ACTUALIZAR
        ComprasResponse compraEncontrada = findById(compra.getId());

        if (compraEncontrada == null) {
            return null;
        }

        compraEncontrada.setProductoId(compra.getProductoId());
        compraEncontrada.setCantidad(compra.getCantidad());
        compraEncontrada.setPrecioUnitario(compra.getPrecioUnitario());
        compraEncontrada.setOrdenCompraId(compra.getOrdenCompraId());
        compraEncontrada.setSubtotal(compra.getSubtotal());

        return compraEncontrada;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return compras.removeIf(compra -> compra.getId() == id);
    }
}    
