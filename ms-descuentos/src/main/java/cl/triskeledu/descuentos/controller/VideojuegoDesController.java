package cl.triskeledu.descuentos.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import cl.triskeledu.descuentos.dto.VideojuegoDesRequest;
import cl.triskeledu.descuentos.dto.VideojuegoDesResponse;
import cl.triskeledu.descuentos.service.VideojuegoDesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/videojuego-descuento")
public class VideojuegoDesController {
    private final VideojuegoDesService vidDesService;

// ─── Métodos auxiliares HATEOAS ───────────────────────────────────────────

    /**
     * Agrega los links de navegación a un DetalleComResponse:
     *   - self         → GET    /api/v1/detalles/{id}
     *   - update       → PUT    /api/v1/detalles/{id}
     *   - delete       → DELETE /api/v1/detalles/{id}
     *   - (no cuenta) disponibles  → GET    /api/v1/recursos/disponibles?disponible=true
     *                    (útil para saber si hay otros recursos disponibles
     *                     del mismo tipo; muy relevante para préstamos/reservas)
     *   - all          → GET    /api/v1/recursos
     */
    private VideojuegoDesResponse addLinks(VideojuegoDesResponse descuento) {
        Long id = descuento.getId();

        descuento.add(linkTo(methodOn(VideojuegoDesController.class).findById(id)).withSelfRel());

        descuento.add(linkTo(methodOn(VideojuegoDesController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar recurso"));

        descuento.add(linkTo(methodOn(VideojuegoDesController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar recurso"));

        // Link a recursos disponibles: muy útil para clientes que quieren
        // saber cuáles están disponibles para préstamo antes de hacer una solicitud.
        //recurso.add(linkTo(methodOn(RecursoFisicoController.class).findByDisponible(true))
        //        .withRel("disponibles").withTitle("GET - Recursos disponibles"));

        descuento.add(linkTo(methodOn(VideojuegoDesController.class).findByCampanaId(descuento.getCampanaId()))
        .withRel("cliente-rut")
        .withTitle("GET - Detalles de compra por RUT de cliente"));
        
        descuento.add(linkTo(methodOn(VideojuegoDesController.class).findByVideojuegoSku(descuento.getVideojuegoSku()))
        .withRel("videojuego-sku")
        .withTitle("GET - Detalles de compra por Sku de videojuego"));
        
        descuento.add(linkTo(methodOn(VideojuegoDesController.class).findByEstado(descuento.getEstado()))
        .withRel("videojuego-sku")
        .withTitle("GET - Detalles de compra por Sku de videojuego"));

        descuento.add(linkTo(methodOn(VideojuegoDesController.class).findAll())
                .withRel("all").withTitle("GET - Listado de recursos"));

        return descuento;
    }


    @GetMapping
    public ResponseEntity<CollectionModel<VideojuegoDesResponse>> findAll() {
       List<VideojuegoDesResponse> descuentos = vidDesService.findAll();
       descuentos.forEach(this::addLinks);

        CollectionModel<VideojuegoDesResponse> collection = CollectionModel.of(
                descuentos,
                linkTo(methodOn(VideojuegoDesController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoDesResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(addLinks(vidDesService.findById(id)));
    }

    @GetMapping("/videojuego-sku/{videojuegosku}")
    public ResponseEntity<CollectionModel<VideojuegoDesResponse>> findByVideojuegoSku(@PathVariable String vidSku) {
        List<VideojuegoDesResponse> descuentos = vidDesService.findByVideojuegoSku(vidSku);
        descuentos.forEach(this::addLinks);

        CollectionModel<VideojuegoDesResponse> collection = CollectionModel.of(
            descuentos,
            linkTo(methodOn(VideojuegoDesController.class).findByVideojuegoSku(vidSku)).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/campana-id/{campanaId}")
    public ResponseEntity<CollectionModel<VideojuegoDesResponse>> findByCampanaId(@PathVariable Long camId) {
        List<VideojuegoDesResponse> descuentos = vidDesService.findByCampanaId(camId);
        descuentos.forEach(this::addLinks);

        CollectionModel<VideojuegoDesResponse> collection = CollectionModel.of(
            descuentos,
            linkTo(methodOn(VideojuegoDesController.class).findByCampanaId(camId)).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<CollectionModel<VideojuegoDesResponse>> findByEstado(@PathVariable String estado) {
        List<VideojuegoDesResponse> descuentos = vidDesService.findByEstado(estado);
        descuentos.forEach(this::addLinks);

        CollectionModel<VideojuegoDesResponse> collection = CollectionModel.of(
            descuentos,
            linkTo(methodOn(VideojuegoDesController.class).findByEstado(estado)).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @PostMapping
    public ResponseEntity<VideojuegoDesResponse> create(@Valid @RequestBody VideojuegoDesRequest request) {
        VideojuegoDesResponse creado = addLinks(vidDesService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoDesResponse> update(@PathVariable @NonNull Long id,
    @Valid @RequestBody VideojuegoDesRequest request) {
        return ResponseEntity.ok(addLinks(vidDesService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
        vidDesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
