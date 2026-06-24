package cl.triskeledu.compras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.compras.model.DetalleCompras;

@Repository
public interface DetalleComRepository extends JpaRepository<DetalleCompras, Long>{
    List<DetalleCompras> findByClienteRut(String clienteRut);
    boolean existsByClienteRut(String clienteRut);

    List<DetalleCompras> findByVideojuegoSku(String videojuegoSku);
    boolean existsByVideojuegoSku(String videojuegoSku);
}
