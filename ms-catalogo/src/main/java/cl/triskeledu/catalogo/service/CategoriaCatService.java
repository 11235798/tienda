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
// import cl.triskeledu.common.exception.*;
import cl.triskeledu.catalogo.mapper.CategoriaCatMapper;
import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import cl.triskeledu.catalogo.model.VideojuegoCatalogo;
import cl.triskeledu.catalogo.repository.CategoriaCatRepository;
import cl.triskeledu.catalogo.repository.VideojuegoCatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaCatService {
    private final VideojuegoCatRepository videojuegoRep;
    private final CategoriaCatRepository categoriaRep;
    private final CategoriaCatMapper categoriaMapper;
    // private final CategoriaCatEventProducer categoriaEventProducer;
    // private final RecursoClient recursoClient;

    public List<CategoriaCatResponse> findAll() {
        return categoriaMapper.toResponseList(categoriaRep.findAll());
    }

    public CategoriaCatResponse findById(Long id) {
        return categoriaMapper.toResponse(getCategoriaById(id));
    }

    public CategoriaCatResponse findByNombreCat(String nombre) {
        return categoriaMapper.toResponse(getCategoriaByNombreCat(nombre));
    }

    @Transactional
    public CategoriaCatResponse create(CategoriaCatRequest request) {
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
        // categoriaRep.findByNombreCat(nombre).ifPresent(l -> {
        //     throw new DuplicateResourceException(
        //     "Una categoria", "nombre", nombre, l.getIdCat());
        // });
    }

    private CategoriaCatalogo getCategoriaById(Long id) {
        // return categoriaRep.findById(id).orElseThrow(
        //     () -> new EntityNotFoundException(
        //         "Categorias", "ID", id));  
    }

    private CategoriaCatalogo getCategoriaByNombreCat(String nombre) {
//        return categoriaRep.findByNombreCat(nombre).orElseThrow(
//            () -> new EntityNotFoundException(
//            "Categorias", "nombre", id
//        ));
    }

    private boolean checkMismoNombreCat(Long id, String nombre) {
        CategoriaCatalogo categoria = getCategoriaById(id);
        return nombre.equalsIgnoreCase(categoria.getNombreCat());
    }

    public boolean existsByNombreCat(String nombre) {
        return categoriaRep.existsByNombreCat(nombre);
    }

    @Transactional
    public CategoriaCatResponse update(
    Long id, CategoriaCatRequest request) {
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
        CategoriaCatalogo categoria = getCategoriaById(id);
        // ¿
        // List<String> tablasAsociadas = new ArrayList<>();
        // if (!videojuego.getCategorias().isEmpty())
        //     tablasAsociadas.add("Categorias");
        // if (recursoClient.existsByName(categoria.getNombreCat()))
        // tablasAsociadas.add("Recursos Fisicos")
        // if (!tablasAsociadas.isEmpty())
        //     throw new ReferentialIntegrityException(
        // "Categorias", id, String.join(", ", tablasAsociadas));
        // ?
        categoriaRep.delete(categoria);
        // ¿
        // CategoriaDeletedEvent event =
        // new CategoriaDeletedEvent(categoria.getNombreCat());
        // categoriaEventProducer.sendDeleted(event);
        // ?
    }
}
