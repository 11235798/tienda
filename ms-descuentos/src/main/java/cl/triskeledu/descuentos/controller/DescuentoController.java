package cl.triskeledu.descuentos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.descuentos.dto.DescuentosResponse;
import cl.triskeledu.descuentos.service.DescuentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/descuentos")

public class DescuentoController {
     private final DescuentoService descuentoService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<DescuentosResponse> findAll() {
        return descuentoService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public DescuentosResponse findById(@PathVariable Long id) {
        return descuentoService.findById(id);
    }

    // Crear compra
    @PostMapping
    public DescuentosResponse create(@RequestBody DescuentosResponse descuento) {
        return descuentoService.save(descuento);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public DescuentosResponse update(
            @PathVariable Long id,
            @RequestBody DescuentosResponse     descuento) {

        descuento.setId(id);
        return descuentoService.save(descuento);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return descuentoService.deleteById(id);
    }
}

