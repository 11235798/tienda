package cl.triskeledu.inventario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.inventario.dto.InventarioResponse;
import cl.triskeledu.inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventarios")
public class InventarioController {
private final InventarioService inventarioService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<InventarioResponse> findAll() {
        return inventarioService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public InventarioResponse findById(@PathVariable Long id) {
        return inventarioService.findById(id);
    }

    // Crear compra
    @PostMapping
    public InventarioResponse create(@RequestBody InventarioResponse inventario) {
        return inventarioService.save(inventario);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public InventarioResponse update(
            @PathVariable Long id,
            @RequestBody InventarioResponse     inventario) {

        inventario.setId(id);
        return inventarioService.save(inventario);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return inventarioService.deleteById(id);
    }
}
