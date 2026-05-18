package cl.triskeledu.resenas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.resenas.dto.ResenasResponse;
import cl.triskeledu.resenas.service.ResenasService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Resenas")
public class ResenasController {
private final ResenasService resenasService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<ResenasResponse> findAll() {
        return resenasService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResenasResponse findById(@PathVariable Long id) {
        return resenasService.findById(id);
    }

    // Crear compra
    @PostMapping
    public ResenasResponse create(@RequestBody ResenasResponse resena) {
        return resenasService.save(resena);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public ResenasResponse update(
            @PathVariable Long id,
            @RequestBody ResenasResponse resena) {

        resena.setId(id);
        return resenasService.save(resena);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return resenasService.deleteById(id);
    }
}
