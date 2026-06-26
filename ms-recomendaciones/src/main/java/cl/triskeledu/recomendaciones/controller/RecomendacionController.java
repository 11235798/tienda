package cl.triskeledu.recomendaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import cl.triskeledu.recomendaciones.dto.RecomendacionRequest;
import cl.triskeledu.recomendaciones.dto.RecomendacionResponse;
import cl.triskeledu.recomendaciones.service.RecomendacionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recomendaciones")
@RequiredArgsConstructor
public class RecomendacionController {

    private final RecomendacionService service;

    @GetMapping
    public List<RecomendacionResponse> listar(){

        return service.listar();
    }

    @GetMapping("/{id}")
    public RecomendacionResponse buscarPorId(@PathVariable Integer id){

        return service.buscarPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<RecomendacionResponse> buscarPorUsuario(@PathVariable Integer usuarioId){

        return service.buscarPorUsuario(usuarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecomendacionResponse crear(@RequestBody RecomendacionRequest request){

        return service.crear(request);
    }

    @PutMapping("/{id}/afinidad")
    public RecomendacionResponse actualizarAfinidad(
            @PathVariable Integer id,
            @RequestBody RecomendacionRequest request){

        return service.actualizarAfinidad(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id){

        service.eliminar(id);
    }

}