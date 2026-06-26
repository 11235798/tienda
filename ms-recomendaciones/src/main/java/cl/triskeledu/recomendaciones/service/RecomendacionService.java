package cl.triskeledu.recomendaciones.service;

import cl.triskeledu.recomendaciones.kafka.KafkaProducerService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.recomendaciones.dto.RecomendacionRequest;
import cl.triskeledu.recomendaciones.dto.RecomendacionResponse;
import cl.triskeledu.recomendaciones.dto.UsuarioResponse;
import cl.triskeledu.recomendaciones.dto.VideojuegoResponse;
import cl.triskeledu.recomendaciones.kafka.events.RecomendacionActualizadaEvent;
import cl.triskeledu.recomendaciones.kafka.events.RecomendacionCreadaEvent;
import cl.triskeledu.recomendaciones.kafka.events.RecomendacionEliminadaEvent;
import cl.triskeledu.recomendaciones.mapper.RecomendacionMapper;
import cl.triskeledu.recomendaciones.model.Recomendacion;
import cl.triskeledu.recomendaciones.repository.RecomendacionRepository;
import cl.triskeledu.recomendaciones.client.UsuarioClient;
import cl.triskeledu.recomendaciones.client.CatalogoClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecomendacionService {

    private final KafkaProducerService kafkaProducerService;
    private final RecomendacionRepository recomendacionRepository;
    private final UsuarioClient usuarioClient;
    private final CatalogoClient catalogoClient;

    public List<RecomendacionResponse> listar(){

        return recomendacionRepository.findAll()
        .stream()
        .map(r -> {

            UsuarioResponse usuario =
                    usuarioClient.buscarPorId(r.getUsuarioId());

            VideojuegoResponse videojuego =
                    catalogoClient.buscarPorId(r.getVideojuegoId());

            return RecomendacionMapper.toResponse(
                    r,
                    usuario,
                    videojuego);
        })
        .toList();
    }

    public RecomendacionResponse buscarPorId(Integer id){

        Recomendacion recomendacion = recomendacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada"));

        UsuarioResponse usuario =
                usuarioClient.buscarPorId(recomendacion.getUsuarioId());

        VideojuegoResponse videojuego =
                catalogoClient.buscarPorId(recomendacion.getVideojuegoId());

        return RecomendacionMapper.toResponse(recomendacion,usuario,videojuego);
    }

    public List<RecomendacionResponse> buscarPorUsuario(Integer usuarioId){

        return recomendacionRepository.findByUsuarioId(usuarioId)
        .stream()
        .map(r -> {

            UsuarioResponse usuario =
                    usuarioClient.buscarPorId(r.getUsuarioId());

            VideojuegoResponse videojuego =
                    catalogoClient.buscarPorId(r.getVideojuegoId());

            return RecomendacionMapper.toResponse(
                    r,
                    usuario,
                    videojuego);
        })
        .toList();
    }

    public RecomendacionResponse crear(RecomendacionRequest request){

        if(recomendacionRepository
                .findByUsuarioIdAndVideojuegoId(
                        request.getUsuarioId(),
                        request.getVideojuegoId())
                .isPresent()){

            throw new RuntimeException("El videojuego ya fue recomendado a este usuario.");
        }

        UsuarioResponse usuario = usuarioClient.buscarPorId(request.getUsuarioId());

        VideojuegoResponse videojuego = catalogoClient.buscarPorId(request.getVideojuegoId());

        Recomendacion recomendacion = Recomendacion.builder()
                .usuarioId(usuario.getId())
                .videojuegoId(videojuego.getId())
                .porcentajeAfinidad(request.getPorcentajeAfinidad())
                .estado(request.getEstado())
                .build();

        recomendacionRepository.save(recomendacion);

        RecomendacionCreadaEvent event =
        RecomendacionCreadaEvent.builder()
                .recomendacionId(recomendacion.getId())
                .usuarioId(recomendacion.getUsuarioId())
                .videojuegoId(recomendacion.getVideojuegoId())
                .porcentajeAfinidad(recomendacion.getPorcentajeAfinidad())
                .build();

                kafkaProducerService.enviarRecomendacionCreada(event);

        return RecomendacionMapper.toResponse(recomendacion, usuario, videojuego);
    }

    public RecomendacionResponse actualizarAfinidad(Integer id,RecomendacionRequest request){

        Recomendacion recomendacion = recomendacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada"));

        BigDecimal afinidadAnterior = recomendacion.getPorcentajeAfinidad();

        recomendacion.setPorcentajeAfinidad(request.getPorcentajeAfinidad());

        recomendacionRepository.save(recomendacion);

        UsuarioResponse usuario =
            usuarioClient.buscarPorId(recomendacion.getUsuarioId());

    VideojuegoResponse videojuego =
            catalogoClient.buscarPorId(recomendacion.getVideojuegoId());
        
        RecomendacionActualizadaEvent event =
        RecomendacionActualizadaEvent.builder()
                .recomendacionId(recomendacion.getId())
                .afinidadAnterior(afinidadAnterior)
                .afinidadNueva(recomendacion.getPorcentajeAfinidad())
                .build();

kafkaProducerService
        .enviarRecomendacionActualizada(event);

    return RecomendacionMapper.toResponse(
            recomendacion,
            usuario,
            videojuego);
    }

    public void eliminar(Integer id){

        Recomendacion recomendacion = recomendacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recomendación no encontrada"));
        
        RecomendacionEliminadaEvent event =
        RecomendacionEliminadaEvent.builder()
                .recomendacionId(recomendacion.getId())
                .usuarioId(recomendacion.getUsuarioId())
                .videojuegoId(recomendacion.getVideojuegoId())
                .build();

        kafkaProducerService.enviarRecomendacionEliminada(event);

        recomendacionRepository.delete(recomendacion);
    }

}