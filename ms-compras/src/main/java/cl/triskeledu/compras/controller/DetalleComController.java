package cl.triskeledu.compras.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.compras.dto.ClienteComResponse;
import cl.triskeledu.compras.dto.DetalleComRequest;
import cl.triskeledu.compras.dto.DetalleComResponse;
import cl.triskeledu.compras.service.DetalleComService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detalles")
public class DetalleComController {
    private final DetalleComService detalleService;

    @GetMapping
    public ResponseEntity<List<DetalleComResponse>> findAll() {
        return ResponseEntity.ok(detalleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleComResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(detalleService.findById(id));
    }

    @GetMapping("/cliente-id/{id}")
    public ResponseEntity<List<DetalleComResponse>> findByClienteId(@PathVariable Long id) {
        return ResponseEntity.ok(detalleService.findByClienteId(id));
    }

    @GetMapping("/videojuego-sku/{sku}")
    public ResponseEntity<List<DetalleComResponse>> findByVideojuegoSku(@PathVariable String sku) {
        return ResponseEntity.ok(detalleService.findByVideojuegoSku(sku));
    }

    @PostMapping
    public ResponseEntity<DetalleComResponse> create(@Valid @RequestBody DetalleComRequest request) {
        DetalleComResponse creado = detalleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleComResponse> update(
            @PathVariable @NonNull Long id,
            @Valid @RequestBody DetalleComRequest request) {
        return ResponseEntity.ok(detalleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
        detalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
