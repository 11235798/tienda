package cl.triskeledu.compras.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.compras.dto.VideojuegoComResponse;
import cl.triskeledu.compras.mapper.VideojuegoComMapper;
import cl.triskeledu.compras.model.VideojuegoCompras;
import cl.triskeledu.compras.repository.DetalleComRepository;
import cl.triskeledu.compras.repository.VideojuegoComRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import cl.triskeledu.common.exception.EntityNotFoundException;
import cl.triskeledu.common.exception.ReferentialIntegrityException;
// import cl.triskeledu.recursos.repository.RecursoFisicoRepository;
//import cl.triskeledu.compras.client.CatalogoClient;

@Service
@RequiredArgsConstructor
public class VideojuegoComService {
    private final VideojuegoComRepository videojuegoRepository;
    private final DetalleComRepository detalleRepository;
    //private final CatalogoClient catalogoClient;
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
        if (detalleRepository.existsByVideojuegoSku(videojuego.getSku()))
            tablasAsociadas.add("Detalle Compras");
        // if (catalogoClient.findBySku(videojuego.getSku()) != null)
        //     tablasAsociadas.add("Videojuegos en Catálogo");
        if (!tablasAsociadas.isEmpty()) throw new ReferentialIntegrityException
        ("Videojuego Compras", sku, String.join(", ", tablasAsociadas));
        videojuegoRepository.delete(videojuego);
    }
    /*
// CORRECCIÓN DE TIPO: Feign devuelve un Objeto. Validamos si no es nulo.
        // ¿Agregamos un try-catch por si el microservicio 'ms-catalogo' lanza un 404 (not found)?
        try {
            if (catalogoClient.findBySku(videojuego.getSku()) != null) {
                tablasAsociadas.add("Videojuegos en Catálogo");
            }
        } catch (Exception e) {
            // Si cae aquí (ej: 404 de Feign), significa que NO existe en el catálogo, 
            // por lo tanto no se agrega a 'tablasAsociadas' y permite continuar.
        }
    */

    public VideojuegoCompras findBySku(String sku) {
        return videojuegoRepository.findBySku(sku)
        .orElseThrow(() -> new EntityNotFoundException
        ("Videojuego Compras", "Sku", sku));
    }

    public VideojuegoCompras findById(Long id) {
        return videojuegoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Videojuego Compras", "id", id));
    }
}
