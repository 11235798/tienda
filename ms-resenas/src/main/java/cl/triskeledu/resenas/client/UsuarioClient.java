package cl.triskeledu.resenas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.triskeledu.resenas.dto.UsuarioResponse;

@FeignClient(name = "ms-usuarios")
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    UsuarioResponse obtenerUsuario(@PathVariable Integer id);

}