package cl.triskeledu.resenas.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.triskeledu.resenas.dto.ResenaRequest;
import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.service.ResenaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    /**
     * Obtener todas las reseñas con enlaces hipermedia
     */
    @GetMapping
    public CollectionModel<EntityModel<ResenaResponse>> obtenerTodas() {
        List<EntityModel<ResenaResponse>> resenas = resenaService.obtenerTodas().stream()
                .map(this::construirEntityModel)
                .collect(Collectors.toList());

        // Retorna la colección con su propio enlace "self"
        return CollectionModel.of(resenas, 
                linkTo(methodOn(ResenaController.class).obtenerTodas()).withSelfRel());
    }

    /**
     * Obtener una reseña por ID con sus acciones disponibles
     */
    @GetMapping("/{id}")
    public EntityModel<ResenaResponse> obtenerPorId(@PathVariable Integer id) {
        ResenaResponse resena = resenaService.obtenerPorId(id);
        return construirEntityModel(resena);
    }

    /**
     * Obtener reseñas de un usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public CollectionModel<EntityModel<ResenaResponse>> obtenerPorUsuario(@PathVariable Integer usuarioId) {
        List<EntityModel<ResenaResponse>> resenas = resenaService.obtenerPorUsuario(usuarioId).stream()
                .map(this::construirEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(resenas, 
                linkTo(methodOn(ResenaController.class).obtenerPorUsuario(usuarioId)).withSelfRel());
    }

    /**
     * Obtener reseñas de un videojuego
     */
    @GetMapping("/videojuego/{videojuegoId}")
    public CollectionModel<EntityModel<ResenaResponse>> obtenerPorVideojuego(@PathVariable Integer videojuegoId) {
        List<EntityModel<ResenaResponse>> resenas = resenaService.obtenerPorVideojuego(videojuegoId).stream()
                .map(this::construirEntityModel)
                .collect(Collectors.toList());

        return CollectionModel.of(resenas, 
                linkTo(methodOn(ResenaController.class).obtenerPorVideojuego(videojuegoId)).withSelfRel());
    }

    /**
     * Crear o actualizar reseña (ahora devuelve el recurso con sus links)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ResenaResponse> guardar(@RequestBody ResenaRequest request) {
        ResenaResponse resena = resenaService.guardar(request);
        return construirEntityModel(resena);
    }

    /**
     * Eliminar reseña por ID (Mantiene NO_CONTENT, el cliente ya conoce la acción)
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método helper para centralizar la creación de enlaces hipermedia por reseña
     */
    private EntityModel<ResenaResponse> construirEntityModel(ResenaResponse resena) {
        // Asumiendo que tu ResenaResponse tiene un método getId() o equivalente. 
        // Si se llama distinto (ej: idResena), cámbialo aquí.
        Integer id = resena.getId(); 

        return EntityModel.of(resena,
                // Link a sí misma (self)
                linkTo(methodOn(ResenaController.class).obtenerPorId(id)).withSelfRel(),
                
                // Link para eliminar esta reseña específica
                linkTo(methodOn(ResenaController.class).eliminar(id)).withRel("eliminar"),
                
                // Links contextuales si el cliente quiere explorar más de ese usuario o videojuego
                linkTo(methodOn(ResenaController.class).obtenerPorUsuario(resena.getUsuarioId())).withRel("resenas_usuario"),
                linkTo(methodOn(ResenaController.class).obtenerPorVideojuego(resena.getVideojuegoId())).withRel("resenas_videojuego")
        );
    }
}