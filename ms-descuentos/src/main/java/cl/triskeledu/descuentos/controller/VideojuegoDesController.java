package cl.triskeledu.descuentos.controller;

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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import cl.triskeledu.descuentos.dto.VideojuegoDesRequest;
import cl.triskeledu.descuentos.dto.VideojuegoDesResponse;
import cl.triskeledu.descuentos.service.VideojuegoDesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videojuego-descuento")
public class VideojuegoDesController {
    private final VideojuegoDesService vidDesService;

    @GetMapping
    public ResponseEntity<List<VideojuegoDesResponse>> findAll() {
        return ResponseEntity.ok(vidDesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoDesResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(vidDesService.findById(id));
    }

    @GetMapping("/videojuego-sku/{videojuegosku}")
    public ResponseEntity<List<VideojuegoDesResponse>> findByVideojuegoSku(@PathVariable String vidSku) {
        return ResponseEntity.ok(vidDesService.findByVideojuegoSku(vidSku));
    }

    @GetMapping("/campana-id/{campanaId}")
    public ResponseEntity<List<VideojuegoDesResponse>> findByCampanaId(@PathVariable Long camId) {
        return ResponseEntity.ok(vidDesService.findByCampanaId(camId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<VideojuegoDesResponse>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(vidDesService.findByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<VideojuegoDesResponse> create(@Valid @RequestBody VideojuegoDesRequest request) {
        VideojuegoDesResponse creado = vidDesService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoDesResponse> update(@PathVariable @NonNull Long id,
    @Valid @RequestBody VideojuegoDesRequest request) {
        return ResponseEntity.ok(vidDesService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
        vidDesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
