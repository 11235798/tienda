package cl.triskeledu.descuentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.descuentos.model.CampanaDescuento;
import java.util.Optional;


@Repository
public interface CampanaDesRepository extends JpaRepository<CampanaDescuento, Long>{
    Optional<CampanaDescuento> findByCodigoPromocion(String codigoPromocion);

    boolean existsByCodigoPromocion(String codigoPromocion);
}
