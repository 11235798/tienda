package cl.triskeledu.usuarios.service;


import java.time.LocalDateTime;
import java.util.List;

import cl.triskeledu.usuarios.dto.UsuariosResenas;
import cl.triskeledu.usuarios.repository.UsuariosRepository;

public class UsuariosService {
private final UsuariosRepository usuarioRepository;

    // Constructor para inyección de dependencias (Recomendado en Spring Boot)
    public UsuariosService(UsuariosRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todos
    public List<UsuariosResenas> findAll() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID de usuario
    public UsuariosResenas findById(Long id) {
        return usuarioRepository.findByUsuarioId(id);
    }
    // Guardar (Recibe el Request anidado y lo mapea al formato del repositorio)
    public UsuariosResenas save(UsuariosResenas request) {
        
            UsuariosResenas consolidado = new UsuariosResenas();
        
        // --- Mapeo de Cuenta ---
        consolidado.setUsername(request.getUsername());
        consolidado.setEmail(request.getEmail());
        consolidado.setActivo(true); // Por defecto activo al registrar
        consolidado.setFechaCreacion(LocalDateTime.now());
        // Nota: La contraseña (request.getPassword()) normalmente se encripta aquí antes de guardar en BD real

        return usuarioRepository.save(consolidado);
    }
  

    // Eliminar
    public Boolean deleteById(Long id) {
        return usuarioRepository.deleteByUsuarioId(id);
    }
}
