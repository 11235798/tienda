package cl.triskeledu.catalogo.service;

//import cl.triskeledu.catalogo.client.RecursoClient;
import cl.triskeledu.catalogo.dto.CategoriaCatResponse;
import cl.triskeledu.catalogo.dto.VideojuegoCatRequest;
import cl.triskeledu.catalogo.dto.VideojuegoCatResponse;
//import cl.triskeledu.catalogo.event.VideojuegoEventProducer;
import cl.triskeledu.catalogo.mapper.VideojuegoCatMapper;
import cl.triskeledu.catalogo.model.CategoriaCatalogo;
import cl.triskeledu.catalogo.model.VideojuegoCatalogo;
import cl.triskeledu.catalogo.repository.CategoriaCatRepository;
import cl.triskeledu.catalogo.repository.VideojuegoCatRepository;
//import cl.triskeledu.common.event.VideojuegoCreatedEvent;
//import cl.triskeledu.common.event.VideojuegoDeletedEvent;
//import cl.triskeledu.common.event.VideojuegoUpdatedEvent;
import cl.triskeledu.common.exception.DuplicateResourceException;
import cl.triskeledu.common.exception.EntityNotFoundException;
import cl.triskeledu.common.exception.ReferentialIntegrityException;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas unitarias para VideojuegoService.
 *
 * Mockito permite aislar VideojuegoCatService de sus dependencias reales:
 * repositorios, mapper, cliente externo y productor de eventos.
 */
@ExtendWith(MockitoExtension.class)
class VideojuegoServiceTest {

    // @Mock crea una dependencia simulada, evitando usar la base de datos real.
    @Mock
    private VideojuegoCatRepository juegoRepository;

    // Mock del repositorio de categorías.
    @Mock
    private CategoriaCatRepository categoriaRepository;

    // Mock del mapper para controlar la conversión entre entidad y DTO.
    @Mock
    private VideojuegoCatMapper juegoMapper;

    // Mock del productor de eventos para evitar publicar eventos reales.
    //@Mock
    //private VideojuegoEventProducer juegoEventProducer;

    // Mock del cliente externo para evitar llamadas reales a otro servicio.
    //@Mock
    //private RecursoClient recursoClient;

    // @InjectMocks crea VideojuegoCatService e inyecta automáticamente los mocks anteriores.
    @InjectMocks
    private VideojuegoCatService juegoService;

    // DataFaker genera datos aleatorios para los objetos de prueba.
    private final Faker faker = new Faker();

    /**
     * Configuración común antes de cada prueba.
     *
     * Aquí se define cómo deben comportarse algunos mocks usados por varias pruebas.
     */
    @BeforeEach
    void setUp() {

        /*
         * lenient():
         * Evita que Mockito falle si alguna prueba no usa esta configuración.
         *
         * when(...):
         * Define qué método del mock se está simulando.
         *
         * any(VideojuegoCatalogo.class):
         * Acepta cualquier instancia de Videojuego como argumento.
         *
         * thenAnswer(...):
         * Permite crear una respuesta dinámica usando el argumento recibido.
         */
        lenient().when(juegoMapper.toResponse(any(VideojuegoCatalogo.class))).thenAnswer(invocation -> {
            VideojuegoCatalogo juego = invocation.getArgument(0);

            if (juego == null) return null;

            VideojuegoCatResponse response = new VideojuegoCatResponse();
            response.setIdVid(juego.getIdVid());
            response.setSkuVid(juego.getSkuVid());
            response.setTituloVid(juego.getTituloVid());
            response.setDesarrolladoraVid(juego.getDesarrolladoraVid());
            response.setAnioLanzamientoVid(juego.getAnioLanzamientoVid());
            response.setPlataformaVid(juego.getPlataformaVid());

            if (juego.getCategorias() != null) {
                List<CategoriaCatResponse> catResponses = juego.getCategorias()
                        .stream()
                        .map(c -> {
                            CategoriaCatResponse cr = new CategoriaCatResponse();
                            cr.setId(c.getId());
                            cr.setNombreCat(c.getNombreCat());
                            return cr;
                        })
                        .toList();

                response.setCategorias(catResponses);
            }

            return response;
        });

        /*
         * Simula la conversión de una lista de Videojuego a una lista de VideojuegoCatResponse.
         */
        lenient().when(juegoMapper.toResponseList(anyList())).thenAnswer(invocation -> {
            List<VideojuegoCatalogo> juegos = invocation.getArgument(0);

            if (juegos == null) return null;

            return juegos.stream()
                    .map(juegoMapper::toResponse)
                    .toList();
        });

        /*
         * doAnswer(...):
         * Se usa aquí porque updateEntity(...) es un método void.
         * Permite simular que el mapper copia los datos del request al juego.
         */
        lenient().doAnswer(invocation -> {
            VideojuegoCatRequest request = invocation.getArgument(0);
            VideojuegoCatalogo juego = invocation.getArgument(1);

            if (request != null && juego != null) {
                juego.setSkuVid(request.getSkuVid());
                juego.setTituloVid(request.getTituloVid());
                juego.setDesarrolladoraVid(request.getDesarrolladoraVid());
                juego.setAnioLanzamientoVid(request.getAnioLanzamientoVid());
                juego.setPlataformaVid(request.getPlataformaVid());
            }

            return null;

        }).when(juegoMapper).updateEntity(any(VideojuegoCatRequest.class), any(VideojuegoCatalogo.class));
    }

    /**
     * Genera un Sku ficticio para las pruebas.
     */
    private String generateFakeSku() {
        return "VG-"
                + faker.number().digits(4);
    }

    /**
     * Crea una entidad Videojuego con datos aleatorios.
     */
    private VideojuegoCatalogo crearVideojuegoSimulado(Long id) {
        VideojuegoCatalogo juego = new VideojuegoCatalogo();

        juego.setIdVid(id);
        juego.setSkuVid(generateFakeSku());
        juego.setTituloVid(faker.book().title());
        juego.setPlataformaVid(faker.book().author());
        juego.setDesarrolladoraVid(faker.book().publisher());
        juego.setAnioLanzamientoVid(faker.number().numberBetween(1450, 2026));
        juego.setCategorias(new ArrayList<>());

        return juego;
    }

    /**
     * Crea un VideojuegoCatRequest con datos aleatorios.
     */
    private VideojuegoCatRequest crearVideojuegoCatRequestSimulado() {
        VideojuegoCatRequest request = new VideojuegoCatRequest();

        request.setSkuVid(generateFakeSku());
        request.setTituloVid(faker.book().title());
        request.setPlataformaVid(faker.book().author());
        request.setDesarrolladoraVid(faker.book().publisher());
        request.setAnioLanzamientoVid(faker.number().numberBetween(1450, 2026));

        return request;
    }

    /**
     * Prueba findAll() cuando existen juegos registrados.
     */
    @Test
    void findAll_DeberiaRetornarListaDeVideojuegos_CuandoExistenRegistros() {
        VideojuegoCatalogo juego1 = crearVideojuegoSimulado(1L);
        VideojuegoCatalogo juego2 = crearVideojuegoSimulado(2L);
        VideojuegoCatalogo juego3 = crearVideojuegoSimulado(3L);

        // when(...).thenReturn(...): simula lo que devuelve el repositorio.
        when(juegoRepository.findAll()).thenReturn(List.of(juego1, juego2, juego3));

        List<VideojuegoCatResponse> resultado = juegoService.findAll();

        // assertNotNull: verifica que el resultado no sea null.
        assertNotNull(resultado, "La lista retornada no debe ser nula");

        // assertEquals: compara el valor esperado con el valor obtenido.
        assertEquals(3, resultado.size(), "La lista debe contener exactamente 3 elementos");

        VideojuegoCatResponse primerRegistro = resultado.get(0);

        assertEquals(juego1.getIdVid(), primerRegistro.getIdVid(), "El ID debe coincidir");
        assertEquals(juego1.getSkuVid(), primerRegistro.getSkuVid(), "El Sku debe coincidir");
        assertEquals(juego1.getTituloVid(), primerRegistro.getTituloVid(), "El título debe coincidir");
        assertEquals(juego1.getPlataformaVid(), primerRegistro.getPlataformaVid(), "El autor debe coincidir");
        assertEquals(juego1.getDesarrolladoraVid(), primerRegistro.getDesarrolladoraVid(), "La editorial debe coincidir");
        assertEquals(juego1.getAnioLanzamientoVid(), primerRegistro.getAnioLanzamientoVid(), "El año de publicación debe coincidir");

        // verify: comprueba que el método del mock fue llamado.
        verify(juegoRepository).findAll();
    }

    /**
     * Prueba findById() cuando el ID existe.
     */
    @Test
    void findById_DeberiaRetornarVideojuego_CuandoIdExiste() {
        Long id = 10L;
        VideojuegoCatalogo juego = crearVideojuegoSimulado(id);

        // Simula que el repositorio encuentra el juego.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juego));

        VideojuegoCatResponse resultado = juegoService.findById(id);

        // Verifica que el servicio retorne un objeto válido.
        assertNotNull(resultado);

        // Compara los datos esperados con los datos retornados.
        assertEquals(juego.getIdVid(), resultado.getIdVid());
        assertEquals(juego.getSkuVid(), resultado.getSkuVid());
        assertEquals(juego.getTituloVid(), resultado.getTituloVid());

        // Verifica que se haya buscado por ID.
        verify(juegoRepository).findById(id);
    }

    /**
     * Prueba findById() cuando el ID no existe.
     */
    @Test
    void findById_DeberiaLanzarEntityNotFoundException_CuandoIdNoExiste() {
        Long id = 999L;

        // Simula que el repositorio no encuentra el juego.
        when(juegoRepository.findById(id)).thenReturn(Optional.empty());

        // Verifica que se lance la excepción esperada.
        assertThrows(EntityNotFoundException.class, () -> juegoService.findById(id));

        // Verifica que se haya consultado el repositorio.
        verify(juegoRepository).findById(id);
    }

    /**
     * Prueba findBySkuVid() cuando el Sku existe.
     */
    @Test
    void findBySkuVid_DeberiaRetornarVideojuego_CuandoSkuExiste() {
        String sku = generateFakeSku();

        VideojuegoCatalogo juego = crearVideojuegoSimulado(1L);
        juego.setSkuVid(sku);

        // Simula que el repositorio encuentra el juego por Sku.
        when(juegoRepository.findBySkuVid(sku)).thenReturn(Optional.of(juego));

        VideojuegoCatResponse resultado = juegoService.findBySku(sku);

        // Verifica que el resultado no sea null.
        assertNotNull(resultado);

        // Verifica que los datos retornados sean los esperados.
        assertEquals(sku, resultado.getSkuVid());
        assertEquals(juego.getTituloVid(), resultado.getTituloVid());

        // Verifica que se haya llamado al método correcto del repositorio.
        verify(juegoRepository).findBySkuVid(sku);
    }

    /**
     * Prueba findBySkuVid() cuando el Sku no existe.
     */
    @Test
    void findBySkuVid_DeberiaLanzarEntityNotFoundException_CuandoSkuNoExiste() {
        String sku = generateFakeSku();

        // Simula que no existe un juego con ese Sku.
        when(juegoRepository.findBySkuVid(sku)).thenReturn(Optional.empty());

        // Verifica que se lance la excepción esperada.
        assertThrows(EntityNotFoundException.class, () -> juegoService.findBySku(sku));

        // Verifica que se haya buscado por Sku.
        verify(juegoRepository).findBySkuVid(sku);
    }

    /**
     * Prueba existsBySku() cuando el Sku existe.
     */
    @Test
    void existsBySku_DeberiaRetornarTrue_CuandoElSkuExiste() {
        String sku = generateFakeSku();

        // Simula que el Sku existe.
        when(juegoRepository.existsBySkuVid(sku)).thenReturn(true);

        boolean resultado = juegoService.existsBySku(sku);

        // Verifica que el resultado booleano sea true.
        assertTrue(resultado);

        // Verifica que se haya consultado la existencia del Sku.
        verify(juegoRepository).existsBySkuVid(sku);
    }

    /**
     * Prueba create() cuando el request es válido y el Sku no está duplicado.
     */
    @Test
    void create_DeberiaCrearVideojuegoYEnviarEvento_CuandoRequestEsValidoYSkuEsUnico() {
        VideojuegoCatRequest request = crearVideojuegoCatRequestSimulado();

        // Simula que no existe otro juego con el mismo Sku.
        when(juegoRepository.findBySkuVid(request.getSkuVid())).thenReturn(Optional.empty());

        /*
         * thenAnswer permite modificar y retornar el argumento recibido.
         * Aquí se simula que la base de datos asigna el ID al guardar.
         */
        when(juegoRepository.save(any(VideojuegoCatalogo.class))).thenAnswer(invocation -> {
            VideojuegoCatalogo l = invocation.getArgument(0);
            l.setIdVid(100L);
            return l;
        });

        VideojuegoCatResponse resultado = juegoService.create(request);

        // Verifica que el servicio retorne una respuesta válida.
        assertNotNull(resultado);

        // Verifica que los datos retornados coincidan con lo esperado.
        assertEquals(100L, resultado.getIdVid());
        assertEquals(request.getSkuVid(), resultado.getSkuVid());
        assertEquals(request.getTituloVid(), resultado.getTituloVid());

        // Verifica las llamadas esperadas a los mocks.
        verify(juegoRepository).findBySkuVid(request.getSkuVid());
        verify(juegoRepository).save(any(VideojuegoCatalogo.class));

        // Verifica que se haya generado el evento de creación.
        //verify(juegoEventProducer).sendCreated(any(VideojuegoCreatedEvent.class));
    }

    /**
     * Prueba create() cuando el Sku ya existe.
     */
    @Test
    void create_DeberiaLanzarDuplicateResourceException_CuandoSkuYaExiste() {
        VideojuegoCatRequest request = crearVideojuegoCatRequestSimulado();

        VideojuegoCatalogo juegoExistente = crearVideojuegoSimulado(2L);
        juegoExistente.setSkuVid(request.getSkuVid());

        // Simula que ya existe un juego con el mismo Sku.
        when(juegoRepository.findBySkuVid(request.getSkuVid())).thenReturn(Optional.of(juegoExistente));

        // Verifica que se lance excepción por duplicidad.
        assertThrows(DuplicateResourceException.class, () -> juegoService.create(request));

        // Verifica que se haya validado la existencia del Sku.
        verify(juegoRepository).findBySkuVid(request.getSkuVid());

        // never(): verifica que estos métodos no hayan sido llamados.
        verify(juegoRepository, never()).save(any(VideojuegoCatalogo.class));
        //verify(juegoEventProducer, never()).sendCreated(any());
    }

    /**
     * Prueba update() cuando el Sku no cambia.
     */
    @Test
    void update_DeberiaActualizarVideojuegoYEnviarEvento_CuandoElSkuNoCambia() {
        Long id = 5L;

        VideojuegoCatalogo juegoExistente = crearVideojuegoSimulado(id);
        VideojuegoCatRequest request = crearVideojuegoCatRequestSimulado();

        request.setSkuVid(juegoExistente.getSkuVid());

        // Simula que el juego existe.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juegoExistente));

        // Simula que save() retorna el mismo juego actualizado.
        when(juegoRepository.save(any(VideojuegoCatalogo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VideojuegoCatResponse resultado = juegoService.update(id, request);

        // Verifica que la respuesta no sea null.
        assertNotNull(resultado);

        // Verifica que los datos hayan sido actualizados.
        assertEquals(request.getTituloVid(), resultado.getTituloVid());
        assertEquals(request.getSkuVid(), resultado.getSkuVid());

        // Verifica que se buscó el juego por ID.
        verify(juegoRepository).findById(id);

        // No debe buscar duplicado porque el Sku no cambió.
        verify(juegoRepository, never()).findBySkuVid(anyString());

        // Verifica que se guardó y se envió evento de actualización.
        verify(juegoRepository).save(any(VideojuegoCatalogo.class));
        //verify(juegoEventProducer).sendUpdated(any(VideojuegoUpdatedEvent.class));
    }

    /**
     * Prueba update() cuando el Sku cambia y no está duplicado.
     */
    @Test
    void update_DeberiaActualizarVideojuegoYEnviarEvento_CuandoElSkuCambiaYNoEstaDuplicado() {
        Long id = 5L;

        VideojuegoCatalogo juegoExistente = crearVideojuegoSimulado(id);
        VideojuegoCatRequest request = crearVideojuegoCatRequestSimulado();

        // Simula que el juego existe.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juegoExistente));

        // Simula que el nuevo Sku no está duplicado.
        when(juegoRepository.findBySkuVid(request.getSkuVid())).thenReturn(Optional.empty());

        // Simula que save() retorna la entidad actualizada.
        when(juegoRepository.save(any(VideojuegoCatalogo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VideojuegoCatResponse resultado = juegoService.update(id, request);

        // Verifica que la respuesta sea válida.
        assertNotNull(resultado);

        // Verifica que el Sku fue actualizado.
        assertEquals(request.getSkuVid(), resultado.getSkuVid());

        // Verifica que se buscó el juego original.
        verify(juegoRepository).findById(id);

        // Verifica que se consultó la unicidad del nuevo Sku.
        verify(juegoRepository).findBySkuVid(request.getSkuVid());

        // Verifica que se guardó y se publicó el evento.
        verify(juegoRepository).save(any(VideojuegoCatalogo.class));
        //verify(juegoEventProducer).sendUpdated(any(VideojuegoUpdatedEvent.class));
    }

    /**
     * Prueba update() cuando el nuevo Sku ya pertenece a otro juego.
     */
    @Test
    void update_DeberiaLanzarDuplicateResourceException_CuandoElSkuCambiaAUnSkuDuplicado() {
        Long id = 5L;

        VideojuegoCatalogo juegoExistente = crearVideojuegoSimulado(id);
        VideojuegoCatRequest request = crearVideojuegoCatRequestSimulado();

        VideojuegoCatalogo otroVideojuegoConMismoSku = crearVideojuegoSimulado(8L);
        otroVideojuegoConMismoSku.setSkuVid(request.getSkuVid());

        // Simula que el juego a actualizar existe.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juegoExistente));

        // Simula que el nuevo Sku ya pertenece a otro juego.
        when(juegoRepository.findBySkuVid(request.getSkuVid())).thenReturn(Optional.of(otroVideojuegoConMismoSku));

        // Verifica que se lance excepción por duplicidad.
        assertThrows(DuplicateResourceException.class, () -> juegoService.update(id, request));

        // Verifica las búsquedas realizadas.
        verify(juegoRepository).findById(id);
        verify(juegoRepository).findBySkuVid(request.getSkuVid());

        // No debe guardar ni enviar evento si hay duplicidad.
        verify(juegoRepository, never()).save(any(VideojuegoCatalogo.class));
        //verify(juegoEventProducer, never()).sendUpdated(any());
    }

    /**
     * Prueba deleteById() cuando el juego no tiene asociaciones.
     */
    @Test
    void deleteById_DeberiaEliminarVideojuegoYEnviarEvento_CuandoNoTieneAsociaciones() {
        Long id = 15L;

        VideojuegoCatalogo juego = crearVideojuegoSimulado(id);

        // Simula que el juego existe.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juego));

        // Simula que no existen recursos físicos asociados al Sku.
        //when(recursoClient.existsBySku(juego.getSkuVid())).thenReturn(false);

        juegoService.deleteById(id);

        // Verifica las llamadas realizadas.
        verify(juegoRepository).findById(id);
        //verify(recursoClient).existsBySku(juego.getSkuVid());
        verify(juegoRepository).delete(juego);

        // Verifica que se haya publicado el evento de eliminación.
        //verify(juegoEventProducer).sendDeleted(any(VideojuegoDeletedEvent.class));
    }

    /**
     * Prueba deleteById() cuando el juego tiene categorías asociadas.
     */
    @Test
    void deleteById_DeberiaLanzarReferentialIntegrityException_CuandoTieneCategoriasAsociadas() {
        Long id = 15L;

        VideojuegoCatalogo juego = crearVideojuegoSimulado(id);

        CategoriaCatalogo categoria = new CategoriaCatalogo();
        categoria.setId(1L);
        categoria.setNombreCat("Novela");

        // Se agrega una categoría para simular una asociación existente.
        juego.getCategorias().add(categoria);

        // Simula que el juego existe.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juego));

        // Simula que no hay recursos físicos, pero sí categoría asociada.
        //when(recursoClient.existsBySku(juego.getSkuVid())).thenReturn(false);

        // Verifica que se lance excepción por integridad referencial.
        assertThrows(ReferentialIntegrityException.class, () -> juegoService.deleteById(id));

        // Verifica las consultas realizadas.
        verify(juegoRepository).findById(id);
        //verify(recursoClient).existsBySku(juego.getSkuVid());

        // No debe eliminar ni enviar evento si tiene asociaciones.
        verify(juegoRepository, never()).delete(any(VideojuegoCatalogo.class));
        //verify(juegoEventProducer, never()).sendDeleted(any());
    }

    /**
     * Prueba deleteById() cuando existen recursos físicos asociados.
     */
    @Test
    void deleteById_DeberiaLanzarReferentialIntegrityException_CuandoTieneRecursosFisicosAsociados() {
        Long id = 15L;

        VideojuegoCatalogo juego = crearVideojuegoSimulado(id);

        // Simula que el juego existe.
        when(juegoRepository.findById(id)).thenReturn(Optional.of(juego));

        // Simula que existen recursos físicos asociados al Sku.
        //when(recursoClient.existsBySku(juego.getSkuVid())).thenReturn(true);

        // Verifica que se lance excepción por integridad referencial.
        assertThrows(ReferentialIntegrityException.class, () -> juegoService.deleteById(id));

        // Verifica las consultas realizadas.
        verify(juegoRepository).findById(id);
        //verify(recursoClient).existsBySku(juego.getSkuVid());

        // No debe eliminar ni enviar evento si existen recursos físicos.
        verify(juegoRepository, never()).delete(any(VideojuegoCatalogo.class));
        //verify(juegoEventProducer, never()).sendDeleted(any());
    }

    /**
     * Prueba addCategoriaAVideojuego() cuando la categoría no estaba asociada.
     */
    @Test
    void addCategoriaAVideojuego_DeberiaAsociarCategoriaYGuardar_CuandoNoEstabaAsociadaPreviamente() {
        Long juegoId = 1L;
        Long categoriaId = 2L;

        VideojuegoCatalogo juego = crearVideojuegoSimulado(juegoId);

        CategoriaCatalogo categoria = new CategoriaCatalogo();
        categoria.setId(categoriaId);
        categoria.setNombreCat("Ficción");

        // Simula que existen el juego y la categoría.
        when(juegoRepository.findById(juegoId)).thenReturn(Optional.of(juego));
        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoria));

        juegoService.addCategoriaAVideojuego(juegoId, categoriaId);

        // Verifica que la categoría fue agregada al juego.
        assertTrue(juego.getCategorias().contains(categoria), "La categoría debería haberse agregado");

        // Verifica las búsquedas y el guardado.
        verify(juegoRepository).findById(juegoId);
        verify(categoriaRepository).findById(categoriaId);
        verify(juegoRepository).save(juego);
    }

    /**
     * Prueba addCategoriaAVideojuego() cuando la categoría ya estaba asociada.
     */
    @Test
    void addCategoriaAVideojuego_NoDeberiaGuardar_CuandoLaCategoriaYaEstabaAsociada() {
        Long juegoId = 1L;
        Long categoriaId = 2L;

        VideojuegoCatalogo juego = crearVideojuegoSimulado(juegoId);

        CategoriaCatalogo categoria = new CategoriaCatalogo();
        categoria.setId(categoriaId);
        categoria.setNombreCat("Ficción");

        // La categoría ya está asociada al juego.
        juego.getCategorias().add(categoria);

        // Simula que existen el juego y la categoría.
        when(juegoRepository.findById(juegoId)).thenReturn(Optional.of(juego));
        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoria));

        juegoService.addCategoriaAVideojuego(juegoId, categoriaId);

        // Verifica las búsquedas realizadas.
        verify(juegoRepository).findById(juegoId);
        verify(categoriaRepository).findById(categoriaId);

        // No se guarda porque la asociación ya existía.
        verify(juegoRepository, never()).save(any(VideojuegoCatalogo.class));
    }
}