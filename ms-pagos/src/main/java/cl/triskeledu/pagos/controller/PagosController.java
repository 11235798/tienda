package cl.triskeledu.pagos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.pagos.dto.PagosResponse;
import cl.triskeledu.pagos.service.PagosService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/pagos")
public class PagosController {
private final PagosService pagosService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<PagosResponse> findAll() {
        return pagosService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public PagosResponse findById(@PathVariable Long id) {
        return pagosService.findById(id);
    }

    // Crear compra
    @PostMapping
    public PagosResponse create(@RequestBody PagosResponse pagos) {
        return pagosService.save(pagos);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public PagosResponse update(
            @PathVariable Long id,
            @RequestBody PagosResponse pagos) {

        pagos.setId(id);
        return pagosService.save(pagos);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return pagosService.deleteById(id);
    }
}


