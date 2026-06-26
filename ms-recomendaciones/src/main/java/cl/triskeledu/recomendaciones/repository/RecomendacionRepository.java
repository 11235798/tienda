package cl.triskeledu.recomendaciones.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.triskeledu.recomendaciones.model.Recomendacion;

public interface RecomendacionRepository extends JpaRepository<Recomendacion, Integer>{

    List<Recomendacion> findByUsuarioId(Integer usuarioId);

    Optional<Recomendacion> findByUsuarioIdAndVideojuegoId(Integer usuarioId,Integer videojuegoId);
}