package cl.triskeledu.descuentos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.descuentos.model.VideojuegoProyeccion;
import java.util.List;


@Repository
public interface VideojuegoProyeccionRepository extends JpaRepository<VideojuegoProyeccion, Long>{
    Optional<VideojuegoProyeccion> findBySku(String sku);

    void deleteBySku(String sku);
}
