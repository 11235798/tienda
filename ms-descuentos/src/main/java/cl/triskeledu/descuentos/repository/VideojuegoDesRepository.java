package cl.triskeledu.descuentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import cl.triskeledu.descuentos.model.VideojuegoDescuento;

@Repository
public interface VideojuegoDesRepository extends JpaRepository<VideojuegoDescuento, Long> {
    boolean existsByVideojuegoId(Long id);
    boolean existsByCampanaId(Long id);
}
