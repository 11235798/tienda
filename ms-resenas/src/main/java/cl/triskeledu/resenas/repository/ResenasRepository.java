package cl.triskeledu.resenas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.triskeledu.resenas.model.Resena;

public interface ResenasRepository extends JpaRepository<Resena,Integer> {
    // Buscar reseñas de un usuario
    List<Resena> findByUsuarioId(Integer usuarioId);

    // Buscar reseñas de un videojuego
    List<Resena> findByVideojuegoId(Integer videojuegoId);

    // Buscar la reseña de un usuario para un videojuego
    Optional<Resena> findByUsuarioIdAndVideojuegoId(
            Integer usuarioId,
            Integer videojuegoId);
}