package cl.triskeledu.catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cl.triskeledu.catalogo.dto.CatalogoRepsonse;
import cl.triskeledu.catalogo.repository.CatalogoRepository;

public class CatalogoService {
     @Autowired
    private CatalogoRepository catalogoRepository;

    // Obtener todos
    public List<CatalogoRepsonse> findAll() {
        return catalogoRepository.findAll();
    }

    // Buscar por ID
    public CatalogoRepsonse findById(Long id) {
        return catalogoRepository.findById(id);
    }

    // Guardar
    public CatalogoRepsonse save(CatalogoRepsonse catalogo) {
        return catalogoRepository.save(catalogo);
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        catalogoRepository.deleteById(id);
        return true;
    }
}

