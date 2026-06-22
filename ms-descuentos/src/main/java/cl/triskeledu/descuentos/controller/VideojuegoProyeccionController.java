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

import cl.triskeledu.descuentos.dto.VideojuegoProyeccionRequest;
import cl.triskeledu.descuentos.dto.VideojuegoProyeccionResponse;
import cl.triskeledu.descuentos.service.VideojuegoProyeccionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videojuego-proyeccion")
public class VideojuegoProyeccionController {
    private final VideojuegoProyeccionService vidProService;
    
    @GetMapping
    public ResponseEntity<List<VideojuegoProyeccionResponse>> findAll() {
        return ResponseEntity.ok(vidProService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoProyeccionResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(vidProService.findById(id));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<VideojuegoProyeccionResponse> findBySku(@PathVariable String sku) {
        return ResponseEntity.ok(vidProService.findBySku(sku));
    }
}
