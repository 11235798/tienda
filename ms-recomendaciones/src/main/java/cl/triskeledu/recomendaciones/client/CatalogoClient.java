package cl.triskeledu.recomendaciones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.triskeledu.recomendaciones.dto.VideojuegoResponse;

@FeignClient(name = "ms-catalogo")
public interface CatalogoClient {

    @GetMapping("/api/catalogo/{id}")
    VideojuegoResponse buscarPorId(@PathVariable Integer id);

}
