package cl.triskeledu.resenas.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.resenas.dto.ResenasResponse;

public class ResenasRepository {
private List<ResenasResponse> resenas = new ArrayList<>();

    // Obtener todos los registros
    public List<ResenasResponse> findAll() {
        return resenas;
    }

    // Buscar por ID usando Streams de Java 8+
    public ResenasResponse findById(Long id) {
        return resenas.stream()
            .filter(o -> o.getId().equals(id)) // Se usa .equals() para comparar objetos Long
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID del producto
    public List<ResenasResponse> findByProductoId(Long productoId) {
        return resenas.stream()
            .filter(o -> o.getProductoId() != null && o.getProductoId().equals(productoId))
            .toList();
    }

    // Buscar por ID del usuario
    public List<ResenasResponse> findByUsuarioId(Long usuarioId) {
        return resenas.stream()
            .filter(o -> o.getUsuarioId() != null && o.getUsuarioId().equals(usuarioId))
            .toList();
    }

    // Buscar por calificación (Ej: traer todas las reseñas de 5 estrellas)
    public List<ResenasResponse> findByCalificacion(Integer calificacion) {
        return resenas.stream()
            .filter(o -> o.getCalificacion() != null && o.getCalificacion().equals(calificacion))
            .toList();
    }
}