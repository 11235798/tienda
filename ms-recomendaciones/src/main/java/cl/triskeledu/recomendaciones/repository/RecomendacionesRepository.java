package cl.triskeledu.recomendaciones.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.recomendaciones.dto.RecomendacionResponse;
import cl.triskeledu.recomendaciones.model.EstadoRecomendacion;

public class RecomendacionesRepository {
private List<RecomendacionResponse> recomendaciones = new ArrayList<>();

    // Obtener todos los registros
    public List<RecomendacionResponse> findAll() {
        return recomendaciones;
    }

    // Buscar por ID usando Streams de Java 8+
    public RecomendacionResponse findById(Long id) {
        return recomendaciones.stream()
            .filter(o -> o.getId().equals(id)) // Se usa .equals() para comparar objetos Long
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID del usuario
    public List<RecomendacionResponse> findByUsuarioId(Long usuarioId) {
        return recomendaciones.stream()
            .filter(o -> o.getUsuarioId() != null && o.getUsuarioId().equals(usuarioId))
            .toList();
    }

    // Buscar por ID del producto
    public List<RecomendacionResponse> findByProductoId(Long productoId) {
        return recomendaciones.stream()
            .filter(o -> o.getProductoId() != null && o.getProductoId().equals(productoId))
            .toList();
    }

    // Buscar por estado de la recomendación
    public List<RecomendacionResponse> findByEstado(EstadoRecomendacion estado) {
        return recomendaciones.stream()
            .filter(o -> o.getEstado() == estado)
            .toList();
    }

    // Guardar o actualizar
    public RecomendacionResponse save(RecomendacionResponse item) {

        // CREAR (Se verifica si es null o 0 para mayor seguridad)
        if (item.getId() == null || item.getId() == 0) {
            Long lastId = (long) (recomendaciones.size() + 1);
            item.setId(lastId);
            recomendaciones.add(item);
            return item;
        }

        // ACTUALIZAR
        RecomendacionResponse itemEncontrado = findById(item.getId());

        if (itemEncontrado == null) {
            return null;
        }

        // Se actualizan todos los campos basados en RecomendacionResponse
        itemEncontrado.setCategoriaInteres(item.getCategoriaInteres());
        itemEncontrado.setNivelInteres(item.getNivelInteres());
        itemEncontrado.setFechaCreacion(item.getFechaCreacion());
        itemEncontrado.setFechaModificacion(item.getFechaModificacion());
        itemEncontrado.setProductoId(item.getProductoId());
        itemEncontrado.setScoreRelevancia(item.getScoreRelevancia());
        itemEncontrado.setAlgoritmoVersion(item.getAlgoritmoVersion());
        itemEncontrado.setEstado(item.getEstado());
        itemEncontrado.setFechaGeneracion(item.getFechaGeneracion());
        itemEncontrado.setUsuarioId(item.getUsuarioId());

        return itemEncontrado;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return recomendaciones.removeIf(item -> item.getId().equals(id));
    }
}

