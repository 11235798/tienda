package cl.triskeledu.usuarios.controller;

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
import org.springframework.web.bind.annotation.RestController;

import cl.triskeledu.usuarios.dto.UsuarioRequest;
import cl.triskeledu.usuarios.dto.UsuarioResponse;
import cl.triskeledu.usuarios.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    //Links-------------------------------------------------------------------
    private UsuarioResponse addLinks(UsuarioResponse usuario) {
        Long id = usuario.getId();

        usuario.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());

        usuario.add(linkTo(methodOn(UsuarioController.class).update(id, null))
                .withRel("update").withTitle("PUT - Actualizar usuario"));

        usuario.add(linkTo(methodOn(UsuarioController.class).deleteById(id))
                .withRel("delete").withTitle("DELETE - Eliminar usuario"));

        usuario.add(linkTo(methodOn(UsuarioController.class).activar(id))
                .withRel("activar").withTitle("PUT - Activar cuenta"));

        usuario.add(linkTo(methodOn(UsuarioController.class).desactivar(id))
                .withRel("desactivar").withTitle("PUT - Desactivar cuenta"));

        usuario.add(linkTo(methodOn(UsuarioController.class).findAll())
                .withRel("all").withTitle("GET - Listado de usuarios"));

        return usuario;
    }

    //El controller es la puerta de entrada al microservicio, valida las solicitudes HTTP del cliente, se asegura que los datos básicos estén correctos y
    //le pasa el trabajo a la capa de Service. Cuando recibe la respuesta, la formatea en JSON y la envía de vuelta al cliente

    //Endpoints--------------------------------------------------------------------------
    @GetMapping
    public ResponseEntity<CollectionModel<UsuarioResponse>> findAll() {
        List<UsuarioResponse> usuarios = usuarioService.findAll();
        usuarios.forEach(this::addLinks);

        CollectionModel<UsuarioResponse> collection = CollectionModel.of(
                usuarios,
                linkTo(methodOn(UsuarioController.class).findAll()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable @NonNull Long id) {
        return ResponseEntity.ok(addLinks(usuarioService.findById(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponse> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(addLinks(usuarioService.findByEmail(email)));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> create(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse creado = addLinks(usuarioService.create(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(
            @PathVariable @NonNull Long id,
            @Valid @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(addLinks(usuarioService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<UsuarioResponse> activar(@PathVariable @NonNull Long id) {
        UsuarioResponse usuario = addLinks(usuarioService.activar(id));
        // Tras activar, el link más relevante es desactivar (el estado complementario)
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<UsuarioResponse> desactivar(@PathVariable @NonNull Long id) {
        UsuarioResponse usuario = addLinks(usuarioService.desactivar(id));
        // Tras desactivar, el link más relevante es activar
        return ResponseEntity.ok(usuario);
    }
}
