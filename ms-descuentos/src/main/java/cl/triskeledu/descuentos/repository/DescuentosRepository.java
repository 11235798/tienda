package cl.triskeledu.descuentos.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.triskeledu.descuentos.dto.DescuentosResponse;

@Repository
public class DescuentosRepository {
      private List<DescuentosResponse> descuentos = new ArrayList<>();

    // Obtener todos los registros
    public List<DescuentosResponse> findAll() {
        return descuentos;
    }

    // Buscar por ID usando Streams de Java 8+
    public DescuentosResponse findById(Long id) {
        return descuentos.stream()
            .filter(o -> o.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID de producto
    public List<DescuentosResponse> findByProductoId(  Long productoId) {
        return descuentos   .stream()
            .filter(o -> o.getId() == productoId)
            .toList();
    }

    // Buscar por ID de orden de compra
    public List<DescuentosResponse> findByOrdenCompraId(Long ordenCompraId) {
        return descuentos.stream()
            .filter(o -> o.getId() == ordenCompraId)
            .toList();
    }

    // Guardar o actualizar
    public DescuentosResponse save(DescuentosResponse descuento) {

        // CREAR
        if (descuento.getId() == 0) {
            Long lastId = (long) (descuentos.size() + 1);
            descuento.setId(lastId);
            descuentos.add(descuento);
            return descuento;
        }

        // ACTUALIZAR
        DescuentosResponse descuentoEncontrado = findById(descuento.getId());

       // Actualizar los campos
        descuento.setCodigoCupon(descuento.getCodigoCupon());
        descuentoEncontrado.setValor(descuento.getValor());
        descuentoEncontrado.setTipoDescuento(descuento.getTipoDescuento());
        descuentoEncontrado.setCampaniaId(descuento.getCampaniaId());
        descuentoEncontrado.setActivo(descuento.getActivo());
        
        // Actualizar la fecha de modificación al momento de guardar
        descuentoEncontrado.setFechaModificacion(LocalDateTime.now());

        return descuentoEncontrado;
    }

    // Eliminar 
    public Boolean deleteById(Long id) {
        return descuentos.removeIf(descuento -> descuento.getId() == id);
    }
}
