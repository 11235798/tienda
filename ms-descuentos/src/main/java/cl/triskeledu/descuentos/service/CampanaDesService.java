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

@Service
@RequiredArgsConstructor
public class CampanaDesService {
    private final CampanaDesRepository campanaRepository;
    private final CampanaDesMapper campanaMapper;
    private final VideojuegoDesRepository videojuegoDesRepository;

    public List<CampanaDesResponse> findAll() {
        return campanaMapper.toResponseList(campanaRepository.findAll());
    }

    private CampanaDescuento getCampanaById(Long id) {
        return campanaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Campañas", "id", id));
    }

    public CampanaDesResponse findById(Long id) {
        return campanaMapper.toResponse(getCampanaById(id));
    }

    private CampanaDescuento getCampanaByCodigo(String codigoPromocion) {
        return campanaRepository.findByCodigoPromocion(codigoPromocion)
        .orElseThrow(() -> new EntityNotFoundException("Campañas", "codigo promocion", codigoPromocion));
    }

    public CampanaDesResponse findByCodigo(String codigoPromocion) {
        return campanaMapper.toResponse(getCampanaByCodigo(codigoPromocion));
    }

    private void validateCodigoUnico(String codigoPromocion) {
        campanaRepository.findByCodigoPromocion(codigoPromocion).ifPresent(l -> 
        {throw new DuplicateResourceException
        ("Una campaña", "codigo promocion", codigoPromocion, l.getNombreCampana());
        });
    }

    @Transactional
    public CampanaDesResponse create(CampanaDesRequest request) {
        validateCodigoUnico(request.getCodigoPromocion());
        CampanaDescuento campana = new CampanaDescuento();
        campanaMapper.updateEntity(request, campana);
        campanaRepository.save(campana);
        // LibroCreatedEvent event = new LibroCreatedEvent(libro.getIsbn(), libro.getTitulo());
        // libroEventProducer.sendCreated(event);
        return campanaMapper.toResponse(campana);
    }

    private boolean checkMismoCodigo(Long id, String codigoPromocion) {
        CampanaDescuento campana = getCampanaById(id);
        return codigoPromocion.equals(campana.getCodigoPromocion());
    }

    public boolean existsByCodigo(String codigoPromocion) {
        return campanaRepository.existsByCodigoPromocion(codigoPromocion);
    }

    @Transactional
    public CampanaDesResponse update(Long id, CampanaDesRequest request) {
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
        CampanaDescuento campana = getCampanaById(id);
        List<String> tablasAsociadas = new ArrayList<>();
        if (videojuegoDesRepository.existsByCampanaId(campana.getId())) tablasAsociadas.add("Recursos Físicos");
        if (!tablasAsociadas.isEmpty())
            throw new ReferentialIntegrityException("Campaña descuento", id, String.join(", ", tablasAsociadas));
    }
}
