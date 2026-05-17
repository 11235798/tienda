package cl.triskeledu.catalogo.repository;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import cl.triskeledu.catalogo.dto.CatalogoRepsonse;

@Repository
public class CatalogoRepository {

    private List<CatalogoRepsonse> productosResponses = new ArrayList<>();

    // Obtener todos los registros
    public List<CatalogoRepsonse> findAll() {
        return productosResponses;
    }

     public CatalogoRepsonse findById(Long id) {
        return productosResponses.stream()
            .filter(o -> o.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Buscar por Nombre (reemplaza a findByProductoId)
    public List<CatalogoRepsonse> findByNombre(String nombre) {
        return productosResponses.stream()
            .filter(o -> o.getNombre() != null && o.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .toList();
    }

    // Buscar por Categoría (reemplaza a findByOrdenCompraId)
    public List<CatalogoRepsonse> findByCategorias(String categorias) {
        return productosResponses.stream()
            .filter(o -> categorias != null && categorias.equals(o.getCategorias()))
            .toList();
    }

    // Guardar o actualizar
    public CatalogoRepsonse save(CatalogoRepsonse producto) {

        // CREAR (asumiendo que id es null o 0 cuando es nuevo)
        if (producto.getId() == null || producto.getId() == 0) {
            Long lastId = (long) (productosResponses.size() + 1);
            producto.setId(lastId);
            productosResponses.add(producto);
            return producto;
        }

        // ACTUALIZAR
        CatalogoRepsonse productoEncontrado = findById(producto.getId());

        if (productoEncontrado == null) {
            return null; // O lanzar una excepción como EntityNotFoundException
        }

        // Actualizamos con los nuevos campos solicitados
        productoEncontrado.setNombre(producto.getNombre());
        productoEncontrado.setDespcripcion(producto.getDespcripcion()); // Corregido el typo "despcripcion"
        productoEncontrado.setDescripcionDetallada(producto.getDescripcionDetallada());
        productoEncontrado.setCategorias(producto.getCategorias());
        productoEncontrado.setPrecio(producto.getPrecio());
        productoEncontrado.setFechaCreacion(producto.getFechaCreacion());
        productoEncontrado.setFechaModificacion(producto.getFechaModificacion());

        return productoEncontrado;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return productosResponses.removeIf(producto -> Long.valueOf(id).equals(producto.getId()));
    }
}

