package cl.triskeledu.catalogo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.catalogo.dto.CatalogoRepsonse;
import cl.triskeledu.catalogo.service.CatalogoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/catalogos")

public class CatalogoController {
    private final CatalogoService catalogoService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<CatalogoRepsonse> findAll() {
        return catalogoService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public CatalogoRepsonse findById(@PathVariable Long id) {
        return catalogoService.findById(id);
    }

    // Crear catalogo
    @PostMapping
    public CatalogoRepsonse create(@RequestBody CatalogoRepsonse catalogo) {
        return catalogoService.save(catalogo);
    }

    // Actualizar catalogo
    @PutMapping("/{id}")
    public CatalogoRepsonse update(
            @PathVariable Long id,
            @RequestBody CatalogoRepsonse     catalogo) {

        catalogo.setId(id);
        return catalogoService.save(catalogo);
    }

    // Eliminar catalogo
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return catalogoService.deleteById(id);
    }
}
