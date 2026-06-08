package cl.triskeledu.compras.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.compras.dto.ClienteComResponse;
import cl.triskeledu.compras.mapper.ClienteComMapper;
import cl.triskeledu.compras.repository.ClienteComRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import cl.triskeledu.catalogo.client.RecursoClient;
// import cl.triskeledu.catalogo.dto.LibroRequest;
// import cl.triskeledu.catalogo.dto.LibroResponse;
// import cl.triskeledu.catalogo.event.LibroEventProducer;
// import cl.triskeledu.common.event.LibroCreatedEvent;
// import cl.triskeledu.common.event.LibroDeletedEvent;
// import cl.triskeledu.common.event.LibroUpdatedEvent;
// import cl.triskeledu.common.exception.*;
// import cl.triskeledu.catalogo.mapper.LibroMapper;
// import cl.triskeledu.catalogo.model.Categoria;
// import cl.triskeledu.catalogo.model.Libro;
// import cl.triskeledu.catalogo.repository.CategoriaRepository;
// import cl.triskeledu.catalogo.repository.LibroRepository;
// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteComService {
    private final ClienteComRepository clienteRepository;
    // private final RecursoFisicoRepository recursoFisicoRepository;
    // private final CatalogoClient catalogoClient;
    private final ClienteComMapper clienteMapper;

    @Transactional
    public List<ClienteComResponse> findAll() {
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    @Transactional
    public void save()
}
