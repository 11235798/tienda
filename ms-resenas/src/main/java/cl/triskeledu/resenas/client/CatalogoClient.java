package cl.triskeledu.resenas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.triskeledu.resenas.dto.VideojuegoResponse;

@FeignClient(name = "ms-videojuegos")
public interface CatalogoClient {

    @GetMapping("/videojuegos/{id}")
    VideojuegoResponse obtenerVideojuego(@PathVariable Integer id);

}
