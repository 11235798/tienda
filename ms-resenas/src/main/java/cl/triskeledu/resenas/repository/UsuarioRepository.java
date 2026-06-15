package cl.triskeledu.resenas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.triskeledu.resenas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}