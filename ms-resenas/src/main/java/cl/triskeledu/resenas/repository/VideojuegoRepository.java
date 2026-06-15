package cl.triskeledu.resenas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.triskeledu.resenas.model.Videojuego;

public interface VideojuegoRepository extends JpaRepository<Videojuego, Integer> {

}