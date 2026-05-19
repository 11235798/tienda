package cl.triskeledu.notificaciones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.notificaciones.dto.NotificasionesResponse;
import cl.triskeledu.notificaciones.service.NotificasionesService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notificaciones")
public class NotificasionesController {
private final NotificasionesService notificacionesService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<NotificasionesResponse> findAll() {
        return notificacionesService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public NotificasionesResponse findById(@PathVariable Long id) {
        return notificacionesService.findById(id);
    }

    // Crear compra
    @PostMapping
    public NotificasionesResponse create(@RequestBody NotificasionesResponse notificacion) {
        return notificacionesService.save(notificacion);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public NotificasionesResponse update(
            @PathVariable Long id,
            @RequestBody NotificasionesResponse notificacion) {

        notificacion.setId(id);
        return notificacionesService.save(notificacion);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return notificacionesService.deleteById(id);
    }
}
