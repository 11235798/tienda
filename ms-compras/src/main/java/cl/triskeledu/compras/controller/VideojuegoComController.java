package cl.triskeledu.compras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.compras.dto.VideojuegoComResponse;
import cl.triskeledu.compras.service.VideojuegoComService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videojuegos-proyeccion")
public class VideojuegoComController {
    private final VideojuegoComService videojuegoService;

    @GetMapping
    public ResponseEntity<List<VideojuegoComResponse>> findAll() {
        return ResponseEntity.ok(videojuegoService.findAll());
    }
}
