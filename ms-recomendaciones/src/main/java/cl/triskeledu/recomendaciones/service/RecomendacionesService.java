package cl.triskeledu.recomendaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.recomendaciones.dto.RecomendacionResponse;
import cl.triskeledu.recomendaciones.repository.RecomendacionesRepository;
@Service
public class RecomendacionesService {
private RecomendacionesRepository recomendacionesRepository;

    // Obtener todos
    public List<RecomendacionResponse> findAll() {
        return recomendacionesRepository.findAll();
    }

    // Buscar por ID
    public RecomendacionResponse findById(Long id) {
        return recomendacionesRepository.findById(id);
    }

    // Guardar
    public RecomendacionResponse save(RecomendacionResponse recomendacion) {
        return recomendacionesRepository.save(recomendacion);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        recomendacionesRepository.deleteById(id);
        return true;
    }
}
