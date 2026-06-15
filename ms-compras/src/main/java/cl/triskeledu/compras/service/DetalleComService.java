package cl.triskeledu.compras.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.compras.dto.DetalleComRequest;
import cl.triskeledu.compras.dto.DetalleComResponse;
import cl.triskeledu.compras.mapper.DetalleComMapper;
import cl.triskeledu.compras.model.ClienteCompras;
import cl.triskeledu.compras.model.DetalleCompras;
import cl.triskeledu.compras.model.VideojuegoCompras;
import cl.triskeledu.compras.repository.ClienteComRepository;
import cl.triskeledu.compras.repository.DetalleComRepository;
import cl.triskeledu.compras.repository.VideojuegoComRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// import cl.triskeledu.catalogo.client.RecursoClient;
// import cl.triskeledu.catalogo.dto.LibroRequest;
// import cl.triskeledu.catalogo.event.LibroEventProducer;
// import cl.triskeledu.common.event.LibroCreatedEvent;
// import cl.triskeledu.common.event.LibroDeletedEvent;
// import cl.triskeledu.common.event.LibroUpdatedEvent;
import cl.triskeledu.common.exception.*;
// import cl.triskeledu.catalogo.mapper.LibroMapper;
// import cl.triskeledu.catalogo.model.Categoria;
// import cl.triskeledu.catalogo.model.Libro;
// import cl.triskeledu.catalogo.repository.CategoriaRepository;
// import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DetalleComService {
    private final DetalleComRepository detalleRepository;
    private final DetalleComMapper detalleMapper;
    private final VideojuegoComRepository videojuegoRepository;
    private final ClienteComRepository clienteRepository;

    public List<DetalleComResponse> findAll() {
        return detalleMapper.toResponseList(detalleRepository.findAll());
    }

    public DetalleComResponse findById(Long id) {
        return detalleMapper.toResponse(getDetalleById(id));
    }

    private DetalleCompras getDetalleById(Long id) {
        return detalleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException
        ("Detalles Compra", "ID", id));
    }

    public List<DetalleComResponse> findByClienteId(Long clienteId) {
        return detalleMapper.toResponseList
        (detalleRepository.findByClienteId(clienteId));
    }

    public List<DetalleComResponse> findByVideojuegoSku(String videojuegoSku) {
        return detalleMapper.toResponseList
        (detalleRepository.findByVideojuegoSku(videojuegoSku));
    }

    private void validateSkuUnico(String sku) {
        detalleRepository.findByVideojuegoSku(sku).ifPresent(r -> {
            throw new DuplicateResourceException
            ("Un detalle compras", "SKU", sku);
        });
    }

    @Transactional
    public DetalleComResponse create(DetalleComRequest request) {
        validateSkuUnico(request.getVideojuegoSku());
        /*if (!catalogoClient.existsByIsbn(request.getIsbn())) {
            throw new EntityNotFoundException
            ("Videojuegos en Catálogo", "Sku", request.getVideojuegoSku());
        } */

        String videojuegoSku = (request.getVideojuegoSku())
        .orElseThrow(() -> new EntityNotFoundException(
            "Videojuegos Compra", "Sku", request.GetVideojuegoSku()
        ));

        Long clienteId = (request.getClienteId())
        .orElseThrow(() -> new EntityNotFoundException(
            "Clientes Compra", "Id", request.getClienteId()
        ));

        DetalleCompras detalle = new DetalleCompras();
        detalleMapper.updateEntity(request, detalle);

        detalle.setClienteId(clienteId);
        detalle.setVideojuegoSku(videojuegoSku);
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioHistorico(request.getPrecioHistorico());
        detalle.setFechaCompra(LocalDateTime.now());

        return detalleMapper.toResponse(detalleRepository.save(detalle));
    }

    @Transactional
    public DetalleComResponse update(Long id, DetalleComRequest request) {
        DetalleCompras detalle = getDetalleById(id);

        String videojuegoSku = (request.getVideojuegoSku())
        .orElseThrow(() -> new EntityNotFoundException(
            "Videojuegos Compra", "Sku", request.getVideojuegoSku()
        ));

        Long clienteId = (request.getClienteId())
        .orElseThrow(() -> new EntityNotFoundException(
            "Clientes Compra", "Id", request.getClienteId()
        ));

        detalleMapper.updateEntity(request, detalle);
        detalle.setClienteId(clienteId);
        detalle.setVideojuegoSku(videojuegoSku);
        detalle.setCantidad(request.getCantidad());
        detalle.setPrecioHistorico(request.getPrecioHistorico());

        return detalleMapper.toResponse(detalleRepository.save(detalle));
    }

    @Transactional
    public void deleteById(Long id) {
        DetalleCompras detalle = getDetalleById(id);
        detalleRepository.delete(detalle);
    }
}
