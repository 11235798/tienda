package cl.triskeledu.compras.controller;

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

import cl.triskeledu.compras.dto.DetalleComRequest;
import cl.triskeledu.compras.dto.DetalleComResponse;
import cl.triskeledu.compras.service.DetalleComService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/detalles")
public class DetalleComController {

    private final DetalleComService detalleService;

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
    private DetalleComResponse addLinks(DetalleComResponse detalle) {
        Long id = detalle.getId();

        detalle.add(linkTo(methodOn(DetalleComController.class).findById(id)).withSelfRel());

        detalle.add(linkTo(methodOn(DetalleComController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar recurso"));

        detalle.add(linkTo(methodOn(DetalleComController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar recurso"));

        // Link a recursos disponibles: muy útil para clientes que quieren
        // saber cuáles están disponibles para préstamo antes de hacer una solicitud.
        //recurso.add(linkTo(methodOn(RecursoFisicoController.class).findByDisponible(true))
        //        .withRel("disponibles").withTitle("GET - Recursos disponibles"));

        detalle.add(linkTo(methodOn(DetalleComController.class).findByClienteRut(detalle.getClienteRut()))
        .withRel("cliente-rut")
        .withTitle("GET - Detalles de compra por RUT de cliente"));
        
        detalle.add(linkTo(methodOn(DetalleComController.class).findByVideojuegoSku(detalle.getVideojuegoSku()))
        .withRel("videojuego-sku")
        .withTitle("GET - Detalles de compra por Sku de videojuego"));

        detalle.add(linkTo(methodOn(DetalleComController.class).findAll())
                .withRel("all").withTitle("GET - Listado de recursos"));

        return detalle;
    }

    // ─── Endpoints ────────────────────────────────────────────────────────────

    @GetMapping
    public ResponseEntity<CollectionModel<DetalleComResponse>> findAll() {
       List<DetalleComResponse> detalles = detalleService.findAll();
       detalles.forEach(this::addLinks);

        CollectionModel<DetalleComResponse> collection = CollectionModel.of(
                detalles,
                linkTo(methodOn(DetalleComController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleComResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(addLinks(detalleService.findById(id)));
    }

    @GetMapping("/cliente-rut/{rut}")
    public ResponseEntity<CollectionModel<DetalleComResponse>> findByClienteRut(@PathVariable String rut) {
        List<DetalleComResponse> detalles = detalleService.findByClienteRut(rut);
        detalles.forEach(this::addLinks);

        CollectionModel<DetalleComResponse> collection = CollectionModel.of(
            detalles,
            linkTo(methodOn(DetalleComController.class).findByClienteRut(rut)).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/videojuego-sku/{sku}")
    public ResponseEntity<CollectionModel<DetalleComResponse>> findByVideojuegoSku(@PathVariable String sku) {
        List<DetalleComResponse> detalles = detalleService.findByVideojuegoSku(sku);
        detalles.forEach(this::addLinks);

        CollectionModel<DetalleComResponse> collection = CollectionModel.of(
            detalles,
            linkTo(methodOn(DetalleComController.class).findByVideojuegoSku(sku)).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @PostMapping
    public ResponseEntity<DetalleComResponse> create(@Valid @RequestBody DetalleComRequest request) {
        DetalleComResponse creado = addLinks(detalleService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleComResponse> update(
            @PathVariable @NonNull Long id,
            @Valid @RequestBody DetalleComRequest request) {
        return ResponseEntity.ok(addLinks(detalleService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
        detalleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
