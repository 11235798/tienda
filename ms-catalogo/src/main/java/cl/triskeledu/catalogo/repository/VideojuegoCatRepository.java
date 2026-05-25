package cl.triskeledu.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.catalogo.model.VideojuegoCatalogo;
import java.util.Optional;

@Repository
public interface VideojuegoCatRepository extends JpaRepository<VideojuegoCatalogo, Long> {
    
    Optional<VideojuegoCatalogo> findBySku(String sku);

    boolean existsBySku(String sku);
}