package cl.triskeledu.usuarios.repository;

import cl.triskeledu.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //El repository es quien se comunica con la base de datos para guardar, actualizar, eliminar o consultar información.

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
