package cl.triskeledu.pagos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.triskeledu.pagos.dto.PagosResponse;
import cl.triskeledu.pagos.repository.PagosRepository;

@Service
public class PagosService {
@Autowired
    private PagosRepository inventarioRepository;

    // Obtener todos
    public List<PagosResponse> findAll() {
        return inventarioRepository.findAll();
    }

    // Buscar por ID
    public PagosResponse findById(Long id) {
        return inventarioRepository.findById(id);
    }

    // Guardar
    public PagosResponse save(PagosResponse inventario) {
        return inventarioRepository.save(inventario);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        inventarioRepository.deleteById(id);
        return true;
    }
}
