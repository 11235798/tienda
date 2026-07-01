package cl.triskeledu.catalogo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

// import cl.triskeledu.catalogo.client.RecursoClient;
import cl.triskeledu.catalogo.dto.VideojuegoCatRequest;
import cl.triskeledu.catalogo.dto.VideojuegoCatResponse;
// import cl.triskeledu.catalogo.event.VideojuegoCatEventProducer;
// import cl.triskeledu.common.event.VideojuegoCreatedEvent;
// import cl.triskeledu.common.event.VideojuegoDeletedEvent;
// import cl.triskeledu.common.event.VideojuegoUpdatedEvent;
import cl.triskeledu.common.exception.*;
import cl.triskeledu.catalogo.mapper.VideojuegoCatMapper;
import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import cl.triskeledu.catalogo.model.VideojuegoCatalogo;
import cl.triskeledu.catalogo.repository.CategoriaCatRepository;
import cl.triskeledu.catalogo.repository.VideojuegoCatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideojuegoCatService {
    private final VideojuegoCatRepository videojuegoRep;
    private final CategoriaCatRepository categoriaRep;
    private final VideojuegoCatMapper videojuegoMapper;
//    private final VideojuegoEventProducer videojuegoEventProducer;
//    private final RecursoClient recursoClient;
    
    public List<VideojuegoCatResponse> findAll() {
        log.debug("Iniciando búsqueda de todos los videojuegos");
        return videojuegoMapper.toResponseList(videojuegoRep.findAll());
    }

    public VideojuegoCatResponse findById(Long id) {
        log.debug("Iniciando búsqueda de videojuego con id '{}'", id);
        return videojuegoMapper.toResponse(getVideojuegoById(id));
    }

    public VideojuegoCatResponse findBySku(String sku) {
        log.debug("Iniciando búsqueda de videojuego con sku '{}'", sku);
        return videojuegoMapper.toResponse(getVideojuegoBySku(sku));
    }

    @Transactional
    public VideojuegoCatResponse create(VideojuegoCatRequest request) {
        log.debug("Iniciando creación de videojuego");
        validateSkuUnico(request.getSkuVid());
        VideojuegoCatalogo videojuego = new VideojuegoCatalogo();
        videojuegoMapper.updateEntity(request, videojuego);
        videojuegoRep.save(videojuego);
//        VideojuegoCreatedEvent event =
//            new VideojuegoCreatedEvent(videojuego.getSku(),
//            videojuego.getTitulo());
//        videojuegoEventProducer.sendCreated(event);
        return videojuegoMapper.toResponse(videojuego);
    }

    private void validateSkuUnico(String sku) {
        log.debug("Validando si el sku '{}'' es único", sku);
        videojuegoRep.findBySkuVid(sku).ifPresent(l -> {
            throw new DuplicateResourceException(
                "Un videojuego", "sku", sku, l.getTituloVid()
            );
        });
    }

    private VideojuegoCatalogo getVideojuegoById(Long id) {
        return videojuegoRep.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
            "Videojuegos", "ID", id));
    }

    private VideojuegoCatalogo getVideojuegoBySku(String sku) {
        return videojuegoRep.findBySkuVid(sku).orElseThrow(
            () -> new EntityNotFoundException(
            "Videojuegos", "sku", sku
        ));
    }

    private boolean checkMismoSku(Long id, String sku) {
        log.debug("Checkeando si el videojuego de id '{}'' tiene el sku '{}'", id, sku);
        VideojuegoCatalogo videojuego = getVideojuegoById(id);
        return sku.equalsIgnoreCase(videojuego.getSkuVid());
    }

    private CategoriaCatalogo getCategoriaById(Long id) {
        log.debug("Iniciando búsqueda de categoría de videojuego por el id '{}'", id);
        return categoriaRep.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
            "Categorias", "ID", id
        ));
    }

    public boolean existsBySku(String sku) {
        log.debug("Verificando si existe un videojuego con sku '{}'", sku);
        return videojuegoRep.existsBySkuVid(sku);
    }

    @Transactional
    public VideojuegoCatResponse update(
    Long id, VideojuegoCatRequest request) {
        log.debug("Iniciando actualización de videojuego con id '{}'", id);
        if (!checkMismoSku(id, request.getSkuVid()))
            validateSkuUnico(request.getSkuVid());
        VideojuegoCatalogo videojuego = new VideojuegoCatalogo();
        videojuegoMapper.updateEntity(request, videojuego);
        videojuegoRep.save(videojuego);
        // VideojuegoUpdatedEvent event =
        // new VideojuegoUpdatedEvent(
        //    videojuego.getSku(), videojuego.getTitulo()
        // );
        // videojuegoEventProducer.sendUpdated(event);
        return videojuegoMapper.toResponse(videojuego);
    }

    @Transactional
    public void deleteById(Long id) {
        log.debug("Iniciando eliminación de videojuego con id '{}'", id);
        VideojuegoCatalogo videojuego = getVideojuegoById(id);
        List<String> tablasAsociadas = new ArrayList<>();
        if (!videojuego.getCategorias().isEmpty())
            tablasAsociadas.add("Categorias");
        // if (recursoClient.existsBySku(videojuego.getSkuVid()))
        //     tablasAsociadas.add("Recursos Fisicos")
        if (!tablasAsociadas.isEmpty())
            throw new ReferentialIntegrityException(
        "Videojuegos", id, String.join(", ", tablasAsociadas));
        videojuegoRep.delete(videojuego);
        // VideojuegoDeletedEvent event =
        // new VideojuegoDeletedEvent(videojuego.getSkuVid());
        // videojuegoEventProducer.sendDeleted(event);
    }

    @Transactional
    public void addCategoriaAVideojuego(Long videojuegoId, Long categoriaId) {
        log.debug("Añadiendo categoría de id '{}' al videojuego de id '{}'", categoriaId, videojuegoId);
        VideojuegoCatalogo videojuego = getVideojuegoById(videojuegoId);
        CategoriaCatalogo categoria = getCategoriaById(categoriaId);
        if (!videojuego.getCategorias().contains(categoria)) {
            videojuego.getCategorias().add(categoria);
            videojuegoRep.save(videojuego);
        }
    }
}
