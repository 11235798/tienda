package cl.triskeledu.compras.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.compras.dto.ComprasResponse;
import cl.triskeledu.compras.service.ComprasService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/compras")

public class ComprasController {

    private final ComprasService comprasService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<ComprasResponse> findAll() {
        return comprasService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ComprasResponse findById(@PathVariable Long id) {
        return comprasService.findById(id);
    }

    // Crear compra
    @PostMapping
    public ComprasResponse create(@RequestBody ComprasResponse compra) {
        return comprasService.save(compra);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public ComprasResponse update(
            @PathVariable Long id,
            @RequestBody ComprasResponse     compra) {

        compra.setId(id);
        return comprasService.save(compra);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return comprasService.deleteById(id);
    }
}
