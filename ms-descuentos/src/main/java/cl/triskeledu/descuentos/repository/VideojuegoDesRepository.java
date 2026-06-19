package cl.triskeledu.descuentos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import cl.triskeledu.descuentos.model.VideojuegoDescuento;

@Repository
public interface VideojuegoDesRepository extends JpaRepository<VideojuegoDescuento, Long> {
    Optional<List<VideojuegoDescuento>> findByVideojuegoId(Long vidId);
    
    Optional<List<VideojuegoDescuento>> findByCampanaId(Long camId);
    
    boolean existsByVideojuegoId(Long id);

    boolean existsByCampanaId(Long id);

    Optional<List<VideojuegoDescuento>> findByEstado(String estado);
}
