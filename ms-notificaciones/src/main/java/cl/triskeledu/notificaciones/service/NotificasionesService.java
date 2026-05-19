package cl.triskeledu.notificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.triskeledu.notificaciones.dto.NotificasionesResponse;
import cl.triskeledu.notificaciones.repository.NotificacionesRepository;

@Service
public class NotificasionesService {
@Autowired
    private NotificacionesRepository inventarioRepository;

    // Obtener todos
    public List<NotificasionesResponse> findAll() {
        return inventarioRepository.findAll();
    }

    // Buscar por ID
    public NotificasionesResponse findById(Long id) {
        return inventarioRepository.findById(id);
    }

    // Guardar
    public NotificasionesResponse save(NotificasionesResponse inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        inventarioRepository.deleteById(id);
        return true;
    }
}
