package cl.triskeledu.catalogo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.catalogo.dto.VideojuegoCatRequest;
import cl.triskeledu.catalogo.dto.VideojuegoCatResponse;
import cl.triskeledu.catalogo.service.VideojuegoCatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videojuegos")
public class VideojuegoCatController {
    private final VideojuegoCatService videojuegoSer;

    @GetMapping
    public ResponseEntity<List<VideojuegoCatResponse>> findAll() {
        return ResponseEntity.ok(videojuegoSer.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoCatResponse> 
    findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(videojuegoSer.findById(id));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<VideojuegoCatResponse>
    findBySku(@PathVariable String sku) {
        return ResponseEntity.ok(videojuegoSer.findBySku(sku));
    }

    @PostMapping
    public ResponseEntity<VideojuegoCatResponse>
    create(@Valid @RequestBody VideojuegoCatRequest request) {
        VideojuegoCatResponse creado = videojuegoSer.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoCatResponse>
    update(@PathVariable @NonNull Long id,
        @Valid @RequestBody VideojuegoCatRequest request) {
        return ResponseEntity.ok(videojuegoSer.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteById(@PathVariable @NonNull Long id) {
        videojuegoSer.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    ("/videojuego/{videojuego_id}/categoria/{categoria_id}")
    public void addCategoriaAVideojuego
    (@PathVariable Long videojuego_id,
        @PathVariable Long categoria_id) {
        videojuegoSer.addCategoriaAVideojuego
        (videojuego_id, categoria_id);
    }

    @GetMapping("/existe/sku/{sku}")
    public ResponseEntity<Boolean>
    existBySku(@PathVariable String sku) {
        return ResponseEntity.ok(videojuegoSer.existsBySku(sku));
    }
}
