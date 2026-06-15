package cl.triskeledu.resenas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cl.triskeledu.resenas.dto.ResenaRequest;
import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.service.ResenaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    /**
     * Obtener todas las reseñas
     * GET /api/resenas
     */
    @GetMapping
    public List<ResenaResponse> obtenerTodas() {
        return resenaService.obtenerTodas();
    }

    /**
     * Obtener una reseña por ID
     * GET /api/resenas/{id}
     */
    @GetMapping("/{id}")
    public ResenaResponse obtenerPorId(@PathVariable Integer id) {
        return resenaService.obtenerPorId(id);
    }

    /**
     * Obtener reseñas de un usuario
     * GET /api/resenas/usuario/{usuarioId}
     */
    @GetMapping("/usuario/{usuarioId}")
    public List<ResenaResponse> obtenerPorUsuario(
            @PathVariable Integer usuarioId) {

        return resenaService.obtenerPorUsuario(usuarioId);
    }

    /**
     * Obtener reseñas de un videojuego
     * GET /api/resenas/videojuego/{videojuegoId}
     */
    @GetMapping("/videojuego/{videojuegoId}")
    public List<ResenaResponse> obtenerPorVideojuego(
            @PathVariable Integer videojuegoId) {

        return resenaService.obtenerPorVideojuego(videojuegoId);
    }

    /**
     * Crear o actualizar reseña
     * POST /api/resenas
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResenaResponse guardar(
            @RequestBody ResenaRequest request) {

        return resenaService.guardar(request);
    }

    /**
     * Eliminar reseña por ID
     * DELETE /api/resenas/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        resenaService.eliminar(id);
    }
}