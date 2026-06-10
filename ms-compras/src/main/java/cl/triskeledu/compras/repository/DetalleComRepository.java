package cl.triskeledu.compras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.compras.model.DetalleCompras;

@Repository
public interface DetalleComRepository extends JpaRepository<DetalleCompras, Long>{
    List<DetalleCompras> findByClienteId(Long clienteId);

    List<DetalleCompras> findByVideojuegoSku(String videojuegoSku);

    
}
