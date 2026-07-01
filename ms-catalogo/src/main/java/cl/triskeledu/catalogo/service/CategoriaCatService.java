package cl.triskeledu.catalogo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// import cl.triskeledu.catalogo.client.RecursoClient;
import cl.triskeledu.catalogo.dto.CategoriaCatRequest;
import cl.triskeledu.catalogo.dto.CategoriaCatResponse;
// import cl.triskeledu.catalogo.event.CategoriaCatEventProducer;
// import cl.triskeledu.common.event.CategoriaCreatedEvent;
// import cl.triskeledu.common.event.CategoriaDeletedEvent;
// import cl.triskeledu.common.event.CategoriaUpdatedEvent;
import cl.triskeledu.common.exception.*;
import cl.triskeledu.catalogo.mapper.CategoriaCatMapper;
import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import cl.triskeledu.catalogo.repository.CategoriaCatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaCatService {
    private final CategoriaCatRepository categoriaRep;
    private final CategoriaCatMapper categoriaMapper;
    // private final CategoriaCatEventProducer categoriaEventProducer;
    // private final RecursoClient recursoClient;

    public List<CategoriaCatResponse> findAll() {
        log.debug("Iniciando búsqueda de todas las categorías de videojuegos.");
        return categoriaMapper.toResponseList(categoriaRep.findAll());
    }

    public CategoriaCatResponse findById(Long id) {
        log.debug("Iniciando búsqueda de categoría de videojuegos por el id '{}''.", id);
        return categoriaMapper.toResponse(getCategoriaById(id));
    }

    public CategoriaCatResponse findByNombreCat(String nombre) {
        log.debug("Iniciando búsqueda de categoría de videojuegos por el nombre '{}''.", nombre);
        return categoriaMapper.toResponse(getCategoriaByNombreCat(nombre));
    }

    @Transactional
    public CategoriaCatResponse create(CategoriaCatRequest request) {
        log.debug("Iniciando creación de categoría de videojuegos");
        validateNombreUnico(request.getNombreCat());
        CategoriaCatalogo categoria = new CategoriaCatalogo();  
        categoriaMapper.updateEntity(request, categoria);
        categoriaRep.save(categoria);
    //     CategoriaCreatedEvent event =
    //     new CategoriaCreatedEvent(categoria.getNombreCat());
    //     CategoriaEventProducer.sendCreated(event);
        return categoriaMapper.toResponse(categoria);
    }

    private void validateNombreUnico(String nombre) {
        log.debug("Iniciando validación de nombre único de categoría");
        categoriaRep.findByNombreCat(nombre).ifPresent(l -> {
            throw new DuplicateResourceException(
                "Una categoría", "nombre", nombre, l.getNombreCat()
            );
        });
    }

    private CategoriaCatalogo getCategoriaById(Long id) {
        return categoriaRep.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
                "Categorias", "ID", id
        ));  
    }

    private CategoriaCatalogo getCategoriaByNombreCat(String nombre) {
        return categoriaRep.findByNombreCat(nombre).orElseThrow(
            () -> new EntityNotFoundException(
            "Categorias", "nombre", nombre
        ));
    }

    private boolean checkMismoNombreCat(Long id, String nombre) {
        log.debug("Checkeando si la categoría de videojuegos con id {} tiene el nombre {}", id, nombre);
        CategoriaCatalogo categoria = getCategoriaById(id);
        return nombre.equalsIgnoreCase(categoria.getNombreCat());
    }

    public boolean existsByNombreCat(String nombre) {
        log.debug("Verificando si la categoría de videojuegos con nombre {} existe", nombre);
        return categoriaRep.existsByNombreCat(nombre);
    }

    @Transactional
    public CategoriaCatResponse update(
    Long id, CategoriaCatRequest request) {
        log.debug("Iniciando actualización de la categoría de videojuegos con id {}", id);
        if (!checkMismoNombreCat(id, request.getNombreCat()))
            validateNombreUnico(request.getNombreCat());
        CategoriaCatalogo categoria = new CategoriaCatalogo();
        categoriaMapper.updateEntity(request, categoria);
        categoriaRep.save(categoria);
    //     CategoriaUpdatedEvent event =
    //     new CategoriaUpdatedEvent(
    //        categoria.getSku(), categoria.getTitulo()
    //     );
    //     categoria.sendUpdated(event);
        return categoriaMapper.toResponse(categoria);
    }

    @Transactional
    public void deleteById(Long id) {
        log.debug("Iniciando eliminación de la categoría de videojuegos con id {}", id);
        CategoriaCatalogo categoria = getCategoriaById(id);
        List<String> tablasAsociadas = new ArrayList<>();
        // if (recursoClient.existsByName(categoria.getNombreCat()))
        // tablasAsociadas.add("Recursos Fisicos")
        if (!tablasAsociadas.isEmpty())
            throw new ReferentialIntegrityException(
        "Categorias", id, String.join(", ", tablasAsociadas));
        categoriaRep.delete(categoria);
        // ¿
        // CategoriaDeletedEvent event =
        // new CategoriaDeletedEvent(categoria.getNombreCat());
        // categoriaEventProducer.sendDeleted(event);
        // ?
    }
}
