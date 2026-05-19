package cl.triskeledu.descuentos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.triskeledu.descuentos.dto.DescuentosResponse;
import cl.triskeledu.descuentos.repository.DescuentosRepository;

@Service
public class DescuentoService {
 @Autowired
    private DescuentosRepository descuentosRepository;

    // Obtener todos
    public List<DescuentosResponse> findAll() {
        return descuentosRepository.findAll();
    }

    // Buscar por ID
    public DescuentosResponse findById(Long id) {
        return descuentosRepository.findById(id);
    }

    // Guardar
    public DescuentosResponse save(DescuentosResponse descuento) {
        return descuentosRepository.save(descuento);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        descuentosRepository.deleteById(id);
        return true;
    }
}

