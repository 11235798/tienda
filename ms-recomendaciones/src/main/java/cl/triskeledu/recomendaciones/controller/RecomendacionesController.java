package cl.triskeledu.recomendaciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.recomendaciones.dto.RecomendacionResponse;
import cl.triskeledu.recomendaciones.service.RecomendacionesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Recomendaciones")
public class RecomendacionesController {

private final RecomendacionesService recomendacionesService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<RecomendacionResponse> findAll() {
        return recomendacionesService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public RecomendacionResponse findById(@PathVariable Long id) {
        return recomendacionesService.findById(id);
    }

    // Crear compra
    @PostMapping
    public RecomendacionResponse create(@RequestBody RecomendacionResponse recomendacion) {
        return recomendacionesService.save(recomendacion);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public RecomendacionResponse update(
            @PathVariable Long id,
            @RequestBody RecomendacionResponse recomendacion) {

        recomendacion.setId(id);
        return recomendacionesService.save(recomendacion);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return recomendacionesService.deleteById(id);
    }
}
