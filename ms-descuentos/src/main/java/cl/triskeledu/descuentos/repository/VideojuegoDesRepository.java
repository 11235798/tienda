package cl.triskeledu.descuentos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import cl.triskeledu.descuentos.model.VideojuegoDescuento;

@Repository
public interface VideojuegoDesRepository extends JpaRepository<VideojuegoDescuento, Long> {
    Optional<List<VideojuegoDescuento>> findByVideojuego_Sku(String sku);
    
    Optional<List<VideojuegoDescuento>> findByCampana_Id(Long camId);
    
    boolean existsByVideojuego_Sku(String sku);

    boolean existsByCampana_Id(Long id);

    Optional<List<VideojuegoDescuento>> findByEstado(String estado);
}
