package cl.triskeledu.resenas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.resenas.client.UsuarioClient;
import cl.triskeledu.resenas.client.CatalogoClient;
import cl.triskeledu.resenas.dto.ResenaRequest;
import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.dto.UsuarioResponse;
import cl.triskeledu.resenas.dto.VideojuegoResponse;
import cl.triskeledu.resenas.kafka.KafkaProducer;
import cl.triskeledu.resenas.kafka.events.ResenaActualizadaEvent;
import cl.triskeledu.resenas.kafka.events.ResenaCreadaEvent;
import cl.triskeledu.resenas.kafka.events.ResenaEliminadaEvent;
import cl.triskeledu.resenas.mapper.ResenaMapper;
import cl.triskeledu.resenas.model.Resena;
import cl.triskeledu.resenas.repository.ResenasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenasRepository resenasRepository;  
    private final ResenaMapper resenaMapper;
    private final UsuarioClient usuarioClient;
    private final CatalogoClient videojuegoClient;
    private final KafkaProducer kafkaProducer;

    public List<ResenaResponse> obtenerTodas() {
        return resenasRepository.findAll()
                .stream()
                .map(resena -> {

    UsuarioResponse usuario =
            usuarioClient.obtenerUsuario(resena.getUsuarioId());

    VideojuegoResponse videojuego =
            videojuegoClient.obtenerVideojuego(resena.getVideojuegoId());

    return resenaMapper.toResponse(resena, usuario, videojuego);

})
                .toList();
    }

    public ResenaResponse obtenerPorId(Integer id) {
        Resena resena = resenasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        UsuarioResponse usuario =
                usuarioClient.obtenerUsuario(resena.getUsuarioId());

        VideojuegoResponse videojuego =
                videojuegoClient.obtenerVideojuego(resena.getVideojuegoId());

        return resenaMapper.toResponse(resena, usuario, videojuego);
    }

    public List<ResenaResponse> obtenerPorUsuario(Integer usuarioId) {
        return resenasRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(resena -> {

    UsuarioResponse usuario =
            usuarioClient.obtenerUsuario(resena.getUsuarioId());

    VideojuegoResponse videojuego =
            videojuegoClient.obtenerVideojuego(resena.getVideojuegoId());

    return resenaMapper.toResponse(resena, usuario, videojuego);

})
                .toList();
    }

    public List<ResenaResponse> obtenerPorVideojuego(Integer videojuegoId) {
        return resenasRepository.findByVideojuegoId(videojuegoId)
                .stream()
                .map(resena -> {

    UsuarioResponse usuario =
            usuarioClient.obtenerUsuario(resena.getUsuarioId());

    VideojuegoResponse videojuego =
            videojuegoClient.obtenerVideojuego(resena.getVideojuegoId());

    return resenaMapper.toResponse(resena, usuario, videojuego);

})
                .toList();
    }

    public ResenaResponse guardar(ResenaRequest request) {

    UsuarioResponse usuario = usuarioClient.obtenerUsuario(request.getUsuarioId());

    VideojuegoResponse videojuego = videojuegoClient.obtenerVideojuego(request.getVideojuegoId());

    Resena resenaGuardada = resenasRepository
            .findByUsuarioIdAndVideojuegoId(
                    request.getUsuarioId(),
                    request.getVideojuegoId())
            .map(resenaExistente -> {

                resenaExistente.setCalificacion(request.getCalificacion());
                resenaExistente.setComentario(request.getComentario());

                Resena actualizada = resenasRepository.save(resenaExistente);

                kafkaProducer.enviarResenaActualizada(
                        ResenaActualizadaEvent.builder()
                                .id(actualizada.getId())
                                .usuarioId(actualizada.getUsuarioId())
                                .videojuegoId(actualizada.getVideojuegoId())
                                .calificacion(actualizada.getCalificacion())
                                .comentario(actualizada.getComentario())
                                .build());

                return actualizada;

            })
            .orElseGet(() -> {

                Resena nueva = Resena.builder()
                        .usuarioId(request.getUsuarioId())
                        .videojuegoId(request.getVideojuegoId())
                        .calificacion(request.getCalificacion())
                        .comentario(request.getComentario())
                        .build();

                Resena creada = resenasRepository.save(nueva);

                kafkaProducer.enviarResenaCreada(
                        ResenaCreadaEvent.builder()
                                .id(creada.getId())
                                .usuarioId(creada.getUsuarioId())
                                .videojuegoId(creada.getVideojuegoId())
                                .calificacion(creada.getCalificacion())
                                .comentario(creada.getComentario())
                                .build());

                return creada;
            });

    return resenaMapper.toResponse(resenaGuardada, usuario, videojuego);
}

    public void eliminar(Integer id) {

    Resena resena = resenasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

    kafkaProducer.enviarResenaEliminada(
            ResenaEliminadaEvent.builder()
                    .resenaId(resena.getId())
                    .usuarioId(resena.getUsuarioId())
                    .videojuegoId(resena.getVideojuegoId())
                    .build());

    resenasRepository.delete(resena);
}
}