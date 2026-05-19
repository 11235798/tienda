package cl.triskeledu.pagos.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.pagos.dto.PagosResponse;
import cl.triskeledu.pagos.modelo.EstadoTransaccion;

public class PagosRepository {
private List<PagosResponse> transacciones = new ArrayList<>();

    // Obtener todos los registros
    public List<PagosResponse> findAll() {
        return transacciones;
    }

    // Buscar por ID usando Streams de Java 8+
    public PagosResponse findById(Long id) {
        return transacciones.stream()
            .filter(o -> o.getId().equals(id)) // Se usa .equals() para comparar objetos Long
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID del método de pago (Reemplazo lógico según los nuevos campos)
    public List<PagosResponse> findByMetodoPagoId(Long metodoPagoId) {
        return transacciones.stream()
            .filter(o -> o.getMetodoPagoId() != null && o.getMetodoPagoId().equals(metodoPagoId))
            .toList();
    }

    // Buscar por estado de la transacción (Asumiendo que EstadoTransaccion es un Enum)
    public List<PagosResponse> findByEstado(EstadoTransaccion estado) {
        return transacciones.stream()
            .filter(o -> o.getEstado() == estado)
            .toList();
    }

    // Guardar o actualizar
    public PagosResponse save(PagosResponse item) {

        // CREAR (Se verifica si es null o 0 para mayor seguridad)
        if (item.getId() == null || item.getId() == 0) {
            Long lastId = (long) (transacciones.size() + 1);
            item.setId(lastId);
            transacciones.add(item);
            return item;
        }

        // ACTUALIZAR
        PagosResponse itemEncontrado = findById(item.getId());

        if (itemEncontrado == null) {
            return null;
        }

        // Se actualizan todos los campos nuevos
        itemEncontrado.setNombre(item.getNombre());
        itemEncontrado.setDescripcion(item.getDescripcion());
        itemEncontrado.setActivo(item.getActivo());
        itemEncontrado.setFechaCreacion(item.getFechaCreacion());
        itemEncontrado.setReferenciaExterna(item.getReferenciaExterna());
        itemEncontrado.setMonto(item.getMonto());
        itemEncontrado.setMoneda(item.getMoneda());
        itemEncontrado.setEstado(item.getEstado());
        itemEncontrado.setMetodoPagoId(item.getMetodoPagoId());
        itemEncontrado.setNombreMetodoPago(item.getNombreMetodoPago());
        itemEncontrado.setFechaTransaccion(item.getFechaTransaccion());
        itemEncontrado.setNumeroSerie(item.getNumeroSerie());
        itemEncontrado.setDatosXml(item.getDatosXml());
        itemEncontrado.setTransaccionId(item.getTransaccionId());
        itemEncontrado.setFechaEmision(item.getFechaEmision());

        return itemEncontrado;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return transacciones.removeIf(item -> item.getId().equals(id));
    }
}
