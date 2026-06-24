package cl.triskeledu.descuentos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.triskeledu.descuentos.dto.VideojuegoProyeccionResponse;

@FeignClient(name = "ms-catalogo")
public interface CatalogoClient {
    @GetMapping("api/v1/videojuegos/sku/{sku}")
    VideojuegoProyeccionResponse findBySku(@PathVariable String sku);
}
