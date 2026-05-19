package cl.triskeledu.resenas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.resenas.dto.ResenasResponse;

@Service
public class ResenasService {
private ResenasService resenasRepository;
    // Obtener todos
    public List<ResenasResponse> findAll() {
        return resenasRepository.findAll();
    }

    // Buscar por ID
    public ResenasResponse findById(Long id) {
        return resenasRepository.findById(id);
    }

    // Guardar
    public ResenasResponse save(ResenasResponse resena) {
        return resenasRepository.save(resena);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        resenasRepository.deleteById(id);
        return true;
    }
}


