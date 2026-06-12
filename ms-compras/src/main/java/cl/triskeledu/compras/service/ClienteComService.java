package cl.triskeledu.compras.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.compras.dto.ClienteComResponse;
import cl.triskeledu.compras.mapper.ClienteComMapper;
import cl.triskeledu.compras.model.ClienteCompras;
import cl.triskeledu.compras.repository.ClienteComRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// import cl.triskeledu.catalogo.client.RecursoClient;
// import cl.triskeledu.catalogo.dto.LibroRequest;
// import cl.triskeledu.catalogo.event.LibroEventProducer;
// import cl.triskeledu.common.event.LibroCreatedEvent;
// import cl.triskeledu.common.event.LibroDeletedEvent;
// import cl.triskeledu.common.event.LibroUpdatedEvent;
// import cl.triskeledu.common.exception.*;
// import cl.triskeledu.catalogo.mapper.LibroMapper;
// import cl.triskeledu.catalogo.model.Categoria;
// import cl.triskeledu.catalogo.repository.CategoriaRepository;

@Service
@RequiredArgsConstructor
public class ClienteComService {
    private final ClienteComRepository clienteRepository;
    private final ClienteComMapper clienteMapper;

    @Transactional
    public List<ClienteComResponse> findAll() {
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    @Transactional
    public void save(String rut, String nombre, String email) {
        ClienteCompras cliente = new ClienteCompras();
        cliente.setRut(rut);
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        clienteRepository.save(cliente);
    }

    @Transactional
    public ClienteComResponse update(Long id, ClienteComRequest request) {
        ClienteCompras cliente = findById(id);

        clienteMapper.updateEntity(request, detalle);
        cliente.setRut(rut);
        cliente.setNombre(nombre);
        cliente.setEmail(email);

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public void deleteByRut(String rut) {
        ClienteCompras cliente = findByRut(rut);
        List<String> tablasAsociadas = new ArrayList<>();
        /*if (!recursoFisicoRepository.existsByLibroIsbn(libroProyeccion.getIsbn())) tablasAsociadas.add("Recursos Físicos");
        if (catalogoClient.existsByIsbn(libroProyeccion.getIsbn())) tablasAsociadas.add("Libros en Catálogo");
        if (!tablasAsociadas.isEmpty()) throw new ReferentialIntegrityException("Libro Proyección", isbn, String.join(", ", tablasAsociadas)); */
        clienteRepository.delete(cliente);
    }

    public ClienteCompras findByRut(String rut) {
        return clienteRepository.findByRut(rut)
        /*.orElseThrow(() -> new EntityNotFoundException
        ("Cliente Compras", "Rut", rut))*/;
    }

    public ClienteComResponse findById(Long id) {
        return clienteMapper.toResponse(getClienteById(id));
    }

    private ClienteCompras getClienteById(Long id) {
        return clienteRepository.findById(id)
    //  .orElseThrow(() -> new EntityNotFoundException("Clientes", "ID", id));
    }
}
