package cl.triskeledu.compras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cl.triskeledu.compras.dto.ComprasResponse;
import cl.triskeledu.compras.repository.ComprasRepository;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    // Obtener todos
    public List<ComprasResponse> findAll() {
        return comprasRepository.findAll();
    }

    // Buscar por ID
    public ComprasResponse findById(Long id) {
        return comprasRepository.findById(id);
    }

    // Guardar
    public ComprasResponse save(ComprasResponse compra) {
        return comprasRepository.save(compra);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        comprasRepository.deleteById(id);
        return true;
    }
}
