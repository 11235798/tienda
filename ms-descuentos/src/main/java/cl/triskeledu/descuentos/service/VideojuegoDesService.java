package cl.triskeledu.descuentos.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import cl.triskeledu.common.exception.*;
//import cl.triskeledu.descuentos.client.CatalogoClient;
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
    //private final CatalogoClient catalogoClient;

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

    private List<VideojuegoDescuento> getVideojuegoDesByVidSku(String sku) {
        return vidDesRepository.findByVideojuego_Sku(sku)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Descuento", "sku", sku));
    }

    public List<VideojuegoDesResponse> findByVideojuegoSku(String sku) {
        return vidDesMapper.toResponseList(getVideojuegoDesByVidSku(sku));
    }

    private List<VideojuegoDescuento> getVideojuegoDesByCamId(Long id) {
        return vidDesRepository.findByCampana_Id(id)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Descuento", "id", id));
    }

    public List<VideojuegoDesResponse> findByCampanaId(Long id) {
        return vidDesMapper.toResponseList(getVideojuegoDesByCamId(id));
    }

    private List<VideojuegoDescuento> getVideojuegoDesByEstado(String estado) {
        return vidDesRepository.findByEstado(estado)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Descuento", "estado", estado));
    }

    public List<VideojuegoDesResponse> findByEstado(String estado) {
        return vidDesMapper.toResponseList(getVideojuegoDesByEstado(estado));
    }

    @Transactional
    public VideojuegoDesResponse create (VideojuegoDesRequest request) {
        // if (catalogoClient.findBySku(request.getVideojuegoSku()) == null) {
        //     throw new EntityNotFoundException("Videojuegos en Catálogo", "sku", request.getVideojuegoSku());
        // }

        VideojuegoProyeccion videojuegoPro = vidProRepository
        .findBySku(request.getVideojuegoSku()).
        orElseThrow(() -> new EntityNotFoundException
        ("Videojuegos Proyeccion", "sku", request.getVideojuegoSku()));

        CampanaDescuento campanaDes = camDesRepository
        .findById(request.getCampanaId()).
        orElseThrow(() -> new EntityNotFoundException
        ("Campaña Descuento", "Id", request.getCampanaId()));

        VideojuegoDescuento vidDescuento = new VideojuegoDescuento();
        vidDesMapper.updateEntity(request, vidDescuento);

        vidDescuento.setCampana(campanaDes);
        vidDescuento.setVideojuego(videojuegoPro);
        vidDescuento.setEstado("Activo");

        return vidDesMapper.toResponse(vidDesRepository.save(vidDescuento));
    }

    @Transactional
    public VideojuegoDesResponse update(Long id, VideojuegoDesRequest request) {
        VideojuegoDescuento vidDescuento = getVideojuegoDesById(id);
        vidDesMapper.updateEntity(request, vidDescuento);
        return vidDesMapper.toResponse(vidDescuento);
    }

    @Transactional
    public void deleteById(Long id) {
        VideojuegoDescuento vidDescuento = getVideojuegoDesById(id);
        vidDesRepository.delete(vidDescuento);
    }
}
