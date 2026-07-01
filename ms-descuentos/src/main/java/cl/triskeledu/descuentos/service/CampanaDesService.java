package cl.triskeledu.descuentos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import cl.triskeledu.common.exception.*;
import cl.triskeledu.descuentos.dto.CampanaDesRequest;
import cl.triskeledu.descuentos.dto.CampanaDesResponse;
import cl.triskeledu.descuentos.mapper.CampanaDesMapper;
import cl.triskeledu.descuentos.model.CampanaDescuento;
import cl.triskeledu.descuentos.repository.CampanaDesRepository;
import cl.triskeledu.descuentos.repository.VideojuegoDesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampanaDesService {
    private final CampanaDesRepository campanaRepository;
    private final CampanaDesMapper campanaMapper;
    private final VideojuegoDesRepository videojuegoDesRepository;

    public List<CampanaDesResponse> findAll() {
        log.debug("Iniciando búsqueda de todas las campañas");
        return campanaMapper.toResponseList(campanaRepository.findAll());
    }

    private CampanaDescuento getCampanaById(Long id) {
        log.debug("Iniciando búsqueda de campaña con id '{}'", id);
        return campanaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Campañas", "id", id));
    }

    public CampanaDesResponse findById(Long id) {
        return campanaMapper.toResponse(getCampanaById(id));
    }

    private CampanaDescuento getCampanaByCodigo(String codigoPromocion) {
        log.debug("Iniciando búsqueda de campaña con código '{}'", codigoPromocion);
        return campanaRepository.findByCodigoPromocion(codigoPromocion)
        .orElseThrow(() -> new EntityNotFoundException("Campañas", "codigo promocion", codigoPromocion));
    }

    public CampanaDesResponse findByCodigo(String codigoPromocion) {
        return campanaMapper.toResponse(getCampanaByCodigo(codigoPromocion));
    }

    private void validateCodigoUnico(String codigoPromocion) {
        log.debug("Validando que el código '{}' sea único", codigoPromocion);
        campanaRepository.findByCodigoPromocion(codigoPromocion).ifPresent(l -> 
        {throw new DuplicateResourceException
        ("Una campaña", "codigo promocion", codigoPromocion, l.getNombreCampana());
        });
    }

    @Transactional
    public CampanaDesResponse create(CampanaDesRequest request) {
        log.debug("Iniciando creación de campaña de descuento");
        validateCodigoUnico(request.getCodigoPromocion());
        CampanaDescuento campana = new CampanaDescuento();
        campanaMapper.updateEntity(request, campana);
        campanaRepository.save(campana);
        // LibroCreatedEvent event = new LibroCreatedEvent(libro.getIsbn(), libro.getTitulo());
        // libroEventProducer.sendCreated(event);
        return campanaMapper.toResponse(campana);
    }

    private boolean checkMismoCodigo(Long id, String codigoPromocion) {
        log.debug("Checkeando si el código de promoción '{}' es el mismo que el de la campaña con id '{}'", codigoPromocion, id);
        CampanaDescuento campana = getCampanaById(id);
        return codigoPromocion.equals(campana.getCodigoPromocion());
    }

    public boolean existsByCodigo(String codigoPromocion) {
        log.debug("Verificando si el código '{}' existe");
        return campanaRepository.existsByCodigoPromocion(codigoPromocion);
    }

    @Transactional
    public CampanaDesResponse update(Long id, CampanaDesRequest request) {
        log.debug("Iniciando actualización de campaña con id '{}'", id);
        if (!checkMismoCodigo(id, request.getCodigoPromocion())) validateCodigoUnico(request.getCodigoPromocion());
        CampanaDescuento campana = new CampanaDescuento();
        campanaMapper.updateEntity(request, campana);
        campanaRepository.save(campana);
        // LibroUpdatedEvent event = new LibroUpdatedEvent(libro.getIsbn(), libro.getTitulo());
        // libroEventProducer.sendUpdated(event);
        return campanaMapper.toResponse(campana);
    }

    @Transactional
    public void deleteById(Long id) {
        log.debug("Iniciando eliminación de campaña con id '{}'", id);
        CampanaDescuento campana = getCampanaById(id);
        List<String> tablasAsociadas = new ArrayList<>();
        if (videojuegoDesRepository.existsByCampana_Id(campana.getId())) tablasAsociadas.add("Recursos Físicos");
        if (!tablasAsociadas.isEmpty())
            throw new ReferentialIntegrityException("Campaña descuento", id, String.join(", ", tablasAsociadas));
    }
}
