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

@Service
@RequiredArgsConstructor
public class VideojuegoCatService {
    private final VideojuegoCatRepository videojuegoRep;
    private final CategoriaCatRepository categoriaRep;
    private final VideojuegoCatMapper videojuegoMapper;
//    private final VideojuegoEventProducer videojuegoEventProducer;
//    private final RecursoClient recursoClient;
    
    public List<VideojuegoCatResponse> findAll() {
        return videojuegoMapper.toResponseList(videojuegoRep.findAll());
    }

    public VideojuegoCatResponse findById(Long id) {
        return videojuegoMapper.toResponse(getVideojuegoById(id));
    }

    public VideojuegoCatResponse findBySku(String sku) {
        return videojuegoMapper.toResponse(getVideojuegoBySku(sku));
    }

    @Transactional
    public VideojuegoCatResponse create(VideojuegoCatRequest request) {
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
        videojuegoRep.findBySku(sku).ifPresent(l -> {
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
        return videojuegoRep.findBySku(sku).orElseThrow(
            () -> new EntityNotFoundException(
            "Videojuegos", "sku", sku
        ));
    }

    private boolean checkMismoSku(Long id, String sku) {
        VideojuegoCatalogo videojuego = getVideojuegoById(id);
        return sku.equalsIgnoreCase(videojuego.getSkuVid());
    }

    private CategoriaCatalogo getCategoriaById(Long id) {
        return categoriaRep.findById(id).orElseThrow(
            () -> new EntityNotFoundException(
            "Categorias", "ID", id
        ));
    }

    public boolean existsBySku(String sku) {
        return videojuegoRep.existsBySku(sku);
    }

    @Transactional
    public VideojuegoCatResponse update(
    Long id, VideojuegoCatRequest request) {
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
        VideojuegoCatalogo videojuego = getVideojuegoById(videojuegoId);
        CategoriaCatalogo categoria = getCategoriaById(categoriaId);
        if (!videojuego.getCategorias().contains(categoria)) {
            videojuego.getCategorias().add(categoria);
            videojuegoRep.save(videojuego);
        }
    }
}
