package cl.triskeledu.compras.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.triskeledu.compras.dto.VideojuegoComResponse;

@FeignClient(name = "ms-catalogo")
public interface CatalogoClient {
    @GetMapping("api/v1/videojuegos/sku/{sku}")
    VideojuegoComResponse findBySku(@PathVariable String sku);
}
