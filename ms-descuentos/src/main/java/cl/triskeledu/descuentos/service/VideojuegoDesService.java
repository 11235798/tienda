package cl.triskeledu.descuentos.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import cl.triskeledu.common.exception.*;
import cl.triskeledu.descuentos.dto.VideojuegoDesRequest;
import cl.triskeledu.descuentos.dto.VideojuegoDesResponse;
import cl.triskeledu.descuentos.mapper.VideojuegoDesMapper;
import cl.triskeledu.descuentos.model.CampanaDescuento;
import cl.triskeledu.descuentos.model.VideojuegoDescuento;
import cl.triskeledu.descuentos.model.VideojuegoProyeccion;
import cl.triskeledu.descuentos.repository.CampanaDesRepository;
import cl.triskeledu.descuentos.repository.VideojuegoDesRepository;
import cl.triskeledu.descuentos.repository.VideojuegoProyeccionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideojuegoDesService {
    private final VideojuegoDesRepository vidDesRepository;
    private final VideojuegoDesMapper vidDesMapper;
    private final VideojuegoProyeccionRepository vidProRepository;
    private final CampanaDesRepository camDesRepository;

    public List<VideojuegoDesResponse> findAll() {
        return vidDesMapper.toResponseList(vidDesRepository.findAll());
    }

    private VideojuegoDescuento getVideojuegoDesById(Long id) {
        return vidDesRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Descuento", "id", id));
    }

    public VideojuegoDesResponse findById(Long id) {
        return vidDesMapper.toResponse(getVideojuegoDesById(id));
    }

    private VideojuegoDescuento getVideojuegoDesByVidId(Long id) {
        return vidDesRepository.findByVideojuegoId(id)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Descuento", "id", id));
    }

    public VideojuegoDesResponse findByVideojuegoId(Long id) {
        return vidDesMapper.toResponse(getVideojuegoDesByVidId(id));
    }

    private VideojuegoDescuento getVideojuegoDesByCamId(Long id) {
        return vidDesRepository.findByCampanaId(id)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Descuento", "id", id));
    }

    public VideojuegoDesResponse findByCampanaId(Long id) {
        return vidDesMapper.toResponse(getVideojuegoDesByCamId(id));
    }

    public List<VideojuegoDesResponse> findByEstado(String estado) {
        return vidDesMapper.toResponseList(vidDesRepository.findByEstado(estado));
    }

    @Transactional
    public VideojuegoDesResponse create (VideojuegoDesRequest request) {
        /*
        if (!catalogoClient.existsByIsbn(request.getIsbn())) {
            throw new EntityNotFoundException("Libros en Catálogo", "ISBN", request.getIsbn());
        }
        */
       VideojuegoProyeccion videojuegoPro = vidProRepository
       .findById(request.getVideojuegoId()).
       orElseThrow(() -> new EntityNotFoundException
       ("Videojuegos Proyeccion", "Id", request.getVideojuegoId()));

       CampanaDescuento campanaDes = camDesRepository
       .findById(request.getCampanaId()).
       orElseThrow(() -> new EntityNotFoundException
       ("Campaña Descuento", "Id", request.getCampanaId()));

       VideojuegoDescuento vidDescuento = new VideojuegoDescuento();
       vidDesMapper.updateEntity(request, vidDescuento);

       vidDescuento.setCampanaId(campanaDes);
       vidDescuento.setVideojuegoId(videojuegoPro);
       vidDescuento.setEstado("Activo");

       return vidDesMapper.toResponse(vidDesRepository.save(vidDescuento));
    }
}
