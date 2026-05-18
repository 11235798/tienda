package cl.triskeledu.usuarios.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.usuarios.dto.UsuariosResenas;
import cl.triskeledu.usuarios.service.UsuariosService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Usuarios")
public class UsuarioController {
private final UsuariosService usuariosService;
// Se marca como 'final' para garantizar que no cambie tras la creación
 // Obtener todas las compras
    @GetMapping
    public List<UsuariosResenas> findAll() {
        return usuariosService.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public UsuariosResenas findById(@PathVariable Long id) {
        return usuariosService.findById(id);
    }

    // Crear compra
    @PostMapping
    public UsuariosResenas create(@RequestBody UsuariosResenas usuario) {
        return usuariosService.save(usuario);
    }

    // Actualizar compra
    @PutMapping("/{id}")
    public UsuariosResenas update(
            @PathVariable Long id,
            @RequestBody UsuariosResenas usuario) {
        usuario.setUsuarioId(id); // Asegura que el ID del path se use para
        return usuariosService.save(usuario);
    }

    // Eliminar compra
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return usuariosService.deleteById(id);
    }
}

