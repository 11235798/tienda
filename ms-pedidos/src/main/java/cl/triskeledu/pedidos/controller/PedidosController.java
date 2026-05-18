package cl.triskeledu.pedidos.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.pedidos.dto.PedidosResponse;
import cl.triskeledu.pedidos.service.PedidosService;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/pedidos")
public class PedidosController {
private final PedidosService pedidosService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<PedidosResponse> findAll() {
        return pedidosService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public PedidosResponse findById(@PathVariable Long id) {
        return pedidosService.findById(id);
    }

    // Crear compra
    @PostMapping
    public PedidosResponse create(@RequestBody PedidosResponse pedidos) {
        return pedidosService.save(pedidos);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public PedidosResponse update(
            @PathVariable Long id,
            @RequestBody PedidosResponse pedidos) {

        pedidos.setId(id);
        return pedidosService.save(pedidos);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return pedidosService.deleteById(id);
    }
}
