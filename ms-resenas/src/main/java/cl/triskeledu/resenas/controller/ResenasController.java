package cl.triskeledu.resenas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.service.ResenasService;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenasController {

    @Autowired
    private ResenasService resenasService;

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ResenaResponse>> findAll() {

        return ResponseEntity.ok(resenasService.findAll());
    }

    // Obtener reseña por ID
    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponse> findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(resenasService.findById(id));
    }

    // Buscar reseñas por email
    @GetMapping("/{email}")
    public ResponseEntity<List<ResenaResponse>> findByUsuarioEmail(
            @PathVariable Long email
    ) {

        return ResponseEntity.ok(
                resenasService.findByUsuarioEmail(email)
        );
    }

    // Crear reseña
    @PostMapping
    public ResponseEntity<ResenaResponse> save(
            @RequestBody ResenaResponse resena
    ) {

        return ResponseEntity.ok(
                resenasService.save(resena)
        );
    }

    // Eliminar reseña
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                resenasService.deleteById(id)
        );
    }
}