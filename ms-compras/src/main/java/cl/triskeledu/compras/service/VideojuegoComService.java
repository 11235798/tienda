package cl.triskeledu.compras.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.compras.dto.VideojuegoComResponse;
import cl.triskeledu.compras.mapper.VideojuegoComMapper;
import cl.triskeledu.compras.model.VideojuegoCompras;
import cl.triskeledu.compras.repository.VideojuegoComRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// import cl.triskeledu.common.exception.EntityNotFoundException;
// import cl.triskeledu.common.exception.ReferentialIntegrityException;
// import cl.triskeledu.recursos.repository.RecursoFisicoRepository;
// import cl.triskeledu.recursos.client.CatalogoClient;

@Service
@RequiredArgsConstructor
public class VideojuegoComService {
    private final VideojuegoComRepository videojuegoRepository;
    // private final RecursoFisicoRepository recursoFisicoRepository;
    // private final CatalogoClient catalogoClient;
    private final VideojuegoComMapper videojuegoMapper;

    @Transactional
    public List<VideojuegoComResponse> findAll() {
        return videojuegoMapper.toResponseList(videojuegoRepository.findAll());
    }

    @Transactional
    public void save(String sku, String titulo, String formato, int precioActual) {
        VideojuegoCompras videojuego = new VideojuegoCompras();
        videojuego.setSku(sku);
        videojuego.setTitulo(titulo);
        videojuego.setFormato(formato);
        videojuego.setPrecioActual(precioActual);
        videojuegoRepository.save(videojuego);
    }

    @Transactional
    public void deleteBySku(String sku) {
        VideojuegoCompras videojuego = findBySku(sku);
        List<String> tablasAsociadas = new ArrayList<>();
        /*if (!recursoFisicoRepository.existsByLibroIsbn(libroProyeccion.getIsbn())) tablasAsociadas.add("Recursos Físicos");
        if (catalogoClient.existsByIsbn(libroProyeccion.getIsbn())) tablasAsociadas.add("Libros en Catálogo");
        if (!tablasAsociadas.isEmpty()) throw new ReferentialIntegrityException("Libro Proyección", isbn, String.join(", ", tablasAsociadas));*/
        videojuegoRepository.delete(videojuego);
    }

    public VideojuegoCompras findBySku(String sku) {
        return videojuegoRepository.findBySku(sku)
        /*.orElseThrow(() -> new EntityNotFoundException
        ("Videojuego Compras", "Sku", sku))*/;
    }
}
