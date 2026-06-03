package cl.triskeledu.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.compras.model.VideojuegoCompras;

import java.util.Optional;

@Repository
public interface VideojuegoComRepository {
    Optional<VideojuegoCompras> findBySku(String sku);

    boolean existsBySku(String sku);
}
