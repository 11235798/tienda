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

import cl.triskeledu.catalogo.dto.CategoriaCatRequest;
import cl.triskeledu.catalogo.dto.CategoriaCatResponse;
import cl.triskeledu.catalogo.service.CategoriaCatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categorias")
public class CategoriaCatController {
    private final CategoriaCatService categoriaSer;
    
    @GetMapping
    public ResponseEntity<List<CategoriaCatResponse>> findAll() {
        return ResponseEntity.ok(categoriaSer.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaCatResponse>
    findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(categoriaSer.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<CategoriaCatResponse>
    findByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(categoriaSer.findByNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<CategoriaCatResponse>
    create(@Valid @RequestBody CategoriaCatRequest request) {
        CategoriaCatResponse creado = categoriaSer.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaCatResponse> update(
            @PathVariable @NonNull Long id,
            @Valid @RequestBody CategoriaCatRequest request) {
        return ResponseEntity.ok(categoriaSer.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteById(@PathVariable @NonNull Long id) {
        categoriaSer.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/existe/nombre/{nombre}")
    public ResponseEntity<Boolean>
    existByIsbn(@PathVariable String nombre) {
        return ResponseEntity.ok(categoriaSer.existsByNombre(nombre));
    }
}
