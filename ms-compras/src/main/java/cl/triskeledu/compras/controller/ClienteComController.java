package cl.triskeledu.compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.compras.dto.ClienteComResponse;
import cl.triskeledu.compras.service.ClienteComService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clientes-proyeccion")
public class ClienteComController {
    private final ClienteComService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteComResponse>> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteComResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ClienteComResponse> findByRut(@PathVariable String rut) {
        return ResponseEntity.ok(clienteService.findByRut(rut));
    }
}
