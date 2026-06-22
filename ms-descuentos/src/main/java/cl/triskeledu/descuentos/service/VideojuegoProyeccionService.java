package cl.triskeledu.descuentos.service;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.common.exception.*;
import cl.triskeledu.descuentos.dto.VideojuegoProyeccionResponse;
import cl.triskeledu.descuentos.mapper.VideojuegoProyeccionMapper;
import cl.triskeledu.descuentos.model.VideojuegoProyeccion;
import cl.triskeledu.descuentos.repository.VideojuegoDesRepository;
import cl.triskeledu.descuentos.repository.VideojuegoProyeccionRepository;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideojuegoProyeccionService {
    private final VideojuegoProyeccionRepository videojuegoRepository;
    private final VideojuegoProyeccionMapper videojuegoMapper;
    private final VideojuegoDesRepository videojuegoDesRepository;

    public List<VideojuegoProyeccionResponse> findAll() {
        return videojuegoMapper.toResponseList(videojuegoRepository.findAll());
    }

    public VideojuegoProyeccionResponse findById(Long id) {
        return videojuegoMapper.toResponse(getVideojuegoById(id));
    }

    private VideojuegoProyeccion getVideojuegoById(Long id) {
        return videojuegoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException
        ("Videojuegos", "ID", id));
    }

    @Transactional
    public void save(String sku, String titulo, int precioBase) {
        VideojuegoProyeccion videojuego = new VideojuegoProyeccion();
        videojuego.setSku(sku);
        videojuego.setTitulo(titulo);
        videojuego.setPrecioBase(precioBase);
        videojuegoRepository.save(videojuego);
    }

    public VideojuegoProyeccion findBySku(String sku) {
        return videojuegoRepository.findBySku(sku)
        .orElseThrow(() -> new EntityNotFoundException
        ("Videojuego proyeccion", "sku", sku));
    }

    @Transactional
    public void deleteBySku(String sku) {
        VideojuegoProyeccion videojuego = findBySku(sku);
        List<String> tablasAsociadas = new ArrayList<>();
        if (!videojuegoDesRepository.existsByVideojuegoId(videojuego.getId()))
        {tablasAsociadas.add("Videojuego Descuento");}
        //if (catalogoClient.existsByIsbn(libroProyeccion.getIsbn())) tablasAsociadas.add("Libros en Catálogo");
        if (!tablasAsociadas.isEmpty()) throw new
        ReferentialIntegrityException("Videojuego Proyección", sku, String.join(", ", tablasAsociadas));
        videojuegoRepository.delete(videojuego);
    }
}
