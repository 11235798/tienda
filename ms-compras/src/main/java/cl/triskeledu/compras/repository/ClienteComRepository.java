package cl.triskeledu.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.triskeledu.compras.model.ClienteCompras;

import java.util.Optional;

@Repository
public interface ClienteComRepository {
    Optional<ClienteCompras> findByRut(String rut);

    boolean existsByRut(String rut);
}
