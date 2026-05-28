package cl.triskeledu.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import java.util.Optional;

@Repository
public interface CategoriaCatRepository extends JpaRepository<CategoriaCatalogo, Long> {
    Optional<CategoriaCatalogo> findByNombreCat(String nombre);
    
    boolean existsByNombreCat(String nombre);
}
