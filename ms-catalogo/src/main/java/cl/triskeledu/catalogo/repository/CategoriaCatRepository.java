package cl.triskeledu.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.catalogo.model.CategoriaCatalogo;

@Repository
public interface CategoriaCatRepository extends JpaRepository<CategoriaCatalogo, Long> {
    
}
