package cl.triskeledu.resenas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.repository.ResenasRepository;

@Service
public class ResenasService {

    @Autowired
    private ResenasRepository resenasRepository;
    // Obtener todos
    public List<ResenaResponse> findAll() {
        return resenasRepository.findAll();
    }

    // Buscar por ID
    public ResenaResponse findById(Integer id) {
        return resenasRepository.findById(id);
    }

    // Buscar por Email del usuario
    public List<ResenaResponse> findByUsuarioEmail(Long userEmail) {
        return resenasRepository.findByUsuarioEmail(userEmail);
    }

    // Guardar
    public ResenaResponse save(ResenaResponse resena) {
        return resenasRepository.save(resena);
    }

    // Eliminar
    public Boolean deleteById(Integer id) {
        resenasRepository.deleteById(id);
        return true;
    }
}


