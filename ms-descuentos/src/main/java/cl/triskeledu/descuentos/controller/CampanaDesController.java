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

import cl.triskeledu.descuentos.dto.CampanaDesRequest;
import cl.triskeledu.descuentos.dto.CampanaDesResponse;
import cl.triskeledu.descuentos.service.CampanaDesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/campana-descuento")
public class CampanaDesController {
    private final CampanaDesService camDesService;

    @GetMapping
    public ResponseEntity<List<CampanaDesResponse>> findAll() {
        return ResponseEntity.ok(camDesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampanaDesResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(camDesService.findById(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CampanaDesResponse> findByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(camDesService.findByCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<CampanaDesResponse> create(@Valid @RequestBody CampanaDesRequest request) {
        CampanaDesResponse creada = camDesService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampanaDesResponse> update(@PathVariable @NonNull Long id,
    @Valid @RequestBody CampanaDesRequest request) {
        return ResponseEntity.ok(camDesService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
        camDesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
