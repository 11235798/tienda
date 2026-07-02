package cl.triskeledu.compras.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.compras.dto.ClienteComRequest;
import cl.triskeledu.compras.dto.ClienteComResponse;
import cl.triskeledu.compras.mapper.ClienteComMapper;
import cl.triskeledu.compras.model.ClienteCompras;
import cl.triskeledu.compras.repository.ClienteComRepository;
import cl.triskeledu.compras.repository.DetalleComRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import cl.triskeledu.compras.client.UsuariosClient;

// import cl.triskeledu.catalogo.client.RecursoClient;
// import cl.triskeledu.catalogo.dto.LibroRequest;
// import cl.triskeledu.catalogo.event.LibroEventProducer;
// import cl.triskeledu.common.event.LibroCreatedEvent;
// import cl.triskeledu.common.event.LibroDeletedEvent;
// import cl.triskeledu.common.event.LibroUpdatedEvent;
import cl.triskeledu.common.exception.*;
// import cl.triskeledu.catalogo.mapper.LibroMapper;
// import cl.triskeledu.catalogo.model.Categoria;
// import cl.triskeledu.catalogo.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteComService {
    private final ClienteComRepository clienteRepository;
    private final DetalleComRepository detalleRepository;
    private final ClienteComMapper clienteMapper;
    //private final UsuariosClient usuariosClient;

    @Transactional
    public List<ClienteComResponse> findAll() {
        log.debug("Iniciando búsqueda de todos los clientes en proyección");
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    @Transactional
    public void save(String rut, String nombre, String email, String rol) {
        log.debug("Iniciando creación de cliente con rut '{}'", rut);
        ClienteCompras cliente = new ClienteCompras();
        cliente.setRut(rut);
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        cliente.setRol(rol);
        clienteRepository.save(cliente);
    }

    @Transactional
    public void deleteByRut(String rut) {
        log.debug("Iniciando eliminación de cliente con rut '{}'", rut);
        ClienteCompras cliente = findByRut(rut);
        List<String> tablasAsociadas = new ArrayList<>();
        if (detalleRepository.existsByClienteRut(cliente.getRut()))
            tablasAsociadas.add("Detalle Compras");
        // if (usuariosClient.findByRut(cliente.getRut()) != null)
        //     tablasAsociadas.add("Usuarios Existentes (no proyección");
        if (!tablasAsociadas.isEmpty()) throw new ReferentialIntegrityException
        ("Cliente Compras", rut, String.join(", ", tablasAsociadas));
        clienteRepository.delete(cliente);
    }

    public ClienteCompras findByRut(String rut) {
        log.debug("Iniciando búsqueda de cliente con rut '{}'", rut);
        return clienteRepository.findByRut(rut)
        .orElseThrow(() -> new EntityNotFoundException
        ("Cliente Compras", "Rut", rut));
    }

    public ClienteCompras findById(Long id) {
        log.debug("Iniciando búsqueda de cliente con id '{}'", id);
        return clienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException
        ("Cliente Compras", "ID", id));
    }
}
