package cl.triskeledu.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.triskeledu.inventario.dto.InventarioResponse;
import cl.triskeledu.inventario.repository.InventarioRepository;

@Service
public class InventarioService {
@Autowired
    private InventarioRepository inventarioRepository;

    // Obtener todos
    public List<InventarioResponse> findAll() {
        return inventarioRepository.findAll();
    }

    // Buscar por ID
    public InventarioResponse findById(Long id) {
        return inventarioRepository.findById(id);
    }

    // Guardar
    public InventarioResponse save(InventarioResponse inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        inventarioRepository.deleteById(id);
        return true;
    }
}
