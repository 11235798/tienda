package cl.triskeledu.descuentos.service;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.common.exception.*;
//import cl.triskeledu.descuentos.client.CatalogoClient;
import cl.triskeledu.descuentos.dto.VideojuegoProyeccionResponse;
import cl.triskeledu.descuentos.mapper.VideojuegoProyeccionMapper;
import cl.triskeledu.descuentos.model.VideojuegoProyeccion;
import cl.triskeledu.descuentos.repository.VideojuegoDesRepository;
import cl.triskeledu.descuentos.repository.VideojuegoProyeccionRepository;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideojuegoProyeccionService {
    private final VideojuegoProyeccionRepository videojuegoRepository;
    private final VideojuegoProyeccionMapper videojuegoMapper;
    private final VideojuegoDesRepository videojuegoDesRepository;
//    private final CatalogoClient catalogoClient;

    public List<VideojuegoProyeccionResponse> findAll() {
        log.debug("Iniciando búsqueda de todos los videojuegos en proyección");
        return videojuegoMapper.toResponseList(videojuegoRepository.findAll());
    }

    public VideojuegoProyeccionResponse findById(Long id) {
        log.debug("Iniciando búsqueda de videojuego con id '{}'", id);
        return videojuegoMapper.toResponse(getVideojuegoById(id));
    }

    private VideojuegoProyeccion getVideojuegoById(Long id) {
        return videojuegoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException
        ("Videojuegos", "ID", id));
    }

    @Transactional
    public void save(String sku, String titulo, int precioBase) {
        log.debug("Iniciando creación de videojuego con sku '{}'", sku);
        VideojuegoProyeccion videojuego = new VideojuegoProyeccion();
        videojuego.setSku(sku);
        videojuego.setTitulo(titulo);
        videojuego.setPrecioBase(precioBase);
        videojuegoRepository.save(videojuego);
    }

    public VideojuegoProyeccion findBySku(String sku) {
        log.debug("Iniciando búsqueda de videojuego con sku '{}'", sku);
        return videojuegoRepository.findBySku(sku)
        .orElseThrow(() -> new EntityNotFoundException
        ("Videojuego proyeccion", "sku", sku));
    }

    @Transactional
    public void deleteBySku(String sku) {
        log.debug("Iniciando eliminación de videojuego con sku '{}'", sku);
        VideojuegoProyeccion videojuego = findBySku(sku);
        List<String> tablasAsociadas = new ArrayList<>();
        if (!videojuegoDesRepository.existsByVideojuego_Sku(videojuego.getSku()))
        {tablasAsociadas.add("Videojuego Descuento");}
        // if (catalogoClient.findBySku(videojuego.getSku()) != null) tablasAsociadas.add("Videojuegos en Catálogo");
        if (!tablasAsociadas.isEmpty()) throw new
        ReferentialIntegrityException("Videojuego Proyección", sku, String.join(", ", tablasAsociadas));
        videojuegoRepository.delete(videojuego);
    }
}
