package cl.triskeledu.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.compras.model.DetalleCompras;

@Repository
public interface DetalleComRepository extends JpaRepository<DetalleCompras, Long>{

}
