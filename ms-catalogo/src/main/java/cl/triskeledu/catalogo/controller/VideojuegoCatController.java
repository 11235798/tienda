package cl.triskeledu.catalogo.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.catalogo.dto.VideojuegoCatRequest;
import cl.triskeledu.catalogo.dto.VideojuegoCatResponse;
import cl.triskeledu.catalogo.service.VideojuegoCatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videojuegos")
@Tag(name = "Videojuegos", description = "API para la gestión del catálogo de videojuegos")
public class VideojuegoCatController {

    private final VideojuegoCatService videojuegoSer;

    // ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega al VideojuegoCatResponse los links de navegación estándar:
     *   - self             → GET  /api/v1/videojuegos/{id}
     *   - update           → PUT  /api/v1/videojuegos/{id}
     *   - delete           → DELETE /api/v1/videojuegos/{id}
     *   - agregar-categoria→ POST /api/v1/videojuegos/videojuego/{id}/categoria/{categoriaId}
     *   - all              → GET  /api/v1/libros
     *
     * WebMvcLinkBuilder.linkTo(methodOn(...)) construye la URL real a partir del
     * propio controlador, evitando strings manuales que se desincronizarían si
     * cambia el @RequestMapping.
     */
    private VideojuegoCatResponse addLinks(VideojuegoCatResponse juego) {
        Long id = juego.getIdVid();

        juego.add(linkTo(methodOn(VideojuegoCatController.class).findById(id)).withSelfRel());

        juego.add(linkTo(methodOn(VideojuegoCatController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar videojuego"));

        juego.add(linkTo(methodOn(VideojuegoCatController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar videojuego"));

        // Para el link de categoría no existe un método exacto con param fijo,
        // se construye el template manualmente para mostrar que acepta {categoriaId}.
        Link categoriaLink = Link.of(
                linkTo(VideojuegoCatController.class).toUri() + "/videojuego/" + id + "/categoria/{categoriaId}",
                "agregar-categoria"
        ).withTitle("POST - Asociar categoría al videojuego");
        juego.add(categoriaLink);

        juego.add(linkTo(methodOn(VideojuegoCatController.class).findAll())
                .withRel("all").withTitle("GET - Listado de libros"));

        return juego;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @Operation(summary = "Obtener todos los videojuegos", description = "Retorna la lista completa de videojuegos del catálogo")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = VideojuegoCatResponse.class))))
    })
    @GetMapping
    public ResponseEntity<CollectionModel<VideojuegoCatResponse>> findAll() {
        List<VideojuegoCatResponse> juegos = videojuegoSer.findAll();

        // Agrega links a cada elemento de la lista
        juegos.forEach(this::addLinks);

        // CollectionModel envuelve la lista y le agrega un link "self" al colección completa
        CollectionModel<VideojuegoCatResponse> collection = CollectionModel.of(
                juegos,
                linkTo(methodOn(VideojuegoCatController.class).findAll()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
    }

    @Operation(summary = "Obtener videojuego por ID", description = "Retorna un videojuego según su identificador único")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Videojuego encontrado",
            content = @Content(schema = @Schema(implementation = VideojuegoCatResponse.class))),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoCatResponse> findById(
            @Parameter(description = "ID del videojuego", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        return ResponseEntity.ok(addLinks(videojuegoSer.findById(id)));
    }

    @Operation(summary = "Obtener videojuego por sku", description = "Retorna un videojuego según su código sku")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Videojuego encontrado",
            content = @Content(schema = @Schema(implementation = VideojuegoCatResponse.class))),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado", content = @Content)
    })
    @GetMapping("/sku/{sku}")
    public ResponseEntity<VideojuegoCatResponse> findBySku(
            @Parameter(description = "Sku del libro", required = true, example = "VG-9000")
            @PathVariable String sku) {
        // findBySku también devuelve un videojuego único: merece sus links de navegación
        return ResponseEntity.ok(addLinks(videojuegoSer.findBySku(sku)));
    }

    @Operation(summary = "Crear un nuevo videojuego", description = "Registra un nuevo videojuego en el catálogo")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Videojuego creado exitosamente",
            content = @Content(schema = @Schema(implementation = VideojuegoCatResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VideojuegoCatResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos del videojuego a crear", required = true,
                content = @Content(schema = @Schema(implementation = VideojuegoCatRequest.class)))
            @Valid @RequestBody VideojuegoCatRequest request) {
        VideojuegoCatResponse creado = addLinks(videojuegoSer.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar un videojuego", description = "Actualiza los datos de un videojuego existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Videojuego actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = VideojuegoCatResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoCatResponse> update(
            @Parameter(description = "ID del videojuego a actualizar", required = true, example = "1")
            @PathVariable @NonNull Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Nuevos datos del videojuego", required = true,
                content = @Content(schema = @Schema(implementation = VideojuegoCatRequest.class)))
            @Valid @RequestBody VideojuegoCatRequest request) {
        return ResponseEntity.ok(addLinks(videojuegoSer.update(id, request)));
    }

    @Operation(summary = "Eliminar un videojuego", description = "Elimina un videojuego del catálogo por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Videojuego eliminado exitosamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "Videojuego no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ID del videojuego a eliminar", required = true, example = "1")
            @PathVariable @NonNull Long id) {
        videojuegoSer.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Agregar categoría a un videojuego", description = "Asocia una categoría existente a un videojuego")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Categoría asociada exitosamente", content = @Content),
        @ApiResponse(responseCode = "404", description = "Videojuego o categoría no encontrados", content = @Content)
    })
    @PostMapping
    ("/videojuego/{videojuego_id}/categoria/{categoria_id}")
    public void addCategoriaAVideojuego(
            @Parameter(description = "ID del videojuego", required = true, example = "1")
            @PathVariable Long videojuego_id,
            @Parameter(description = "ID de la categoría", required = true, example = "3")
            @PathVariable Long categoria_id) {
        videojuegoSer.addCategoriaAVideojuego(videojuego_id, categoria_id);
    }

    @Operation(summary = "Verificar existencia por sku", description = "Comprueba si ya existe un videojuego registrado con el sku indicado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Verificación realizada exitosamente",
            content = @Content(schema = @Schema(implementation = Boolean.class)))
    })
    @GetMapping("/existe/sku/{sku}")
    public ResponseEntity<Boolean> existBySku(
            @Parameter(description = "Sku a verificar", required = true, example = "VG-9000")
            @PathVariable String sku) {
        return ResponseEntity.ok(videojuegoSer.existsBySku(sku));
    }
}
