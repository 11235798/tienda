package cl.triskeledu.resenas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.resenas.dto.ResenaRequest;
import cl.triskeledu.resenas.dto.ResenaResponse;
import cl.triskeledu.resenas.mapper.ResenaMapper;
import cl.triskeledu.resenas.model.Resena;
import cl.triskeledu.resenas.model.Usuario;
import cl.triskeledu.resenas.model.Videojuego;
import cl.triskeledu.resenas.repository.ResenasRepository;
import cl.triskeledu.resenas.repository.UsuarioRepository;
import cl.triskeledu.resenas.repository.VideojuegoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenasRepository resenasRepository;
    private final UsuarioRepository usuarioRepository;
    private final VideojuegoRepository videojuegoRepository;
    private final ResenaMapper resenaMapper;

    public List<ResenaResponse> obtenerTodas() {
        return resenasRepository.findAll()
                .stream()
                .map(resenaMapper::toResponse)
                .toList();
    }

    public ResenaResponse obtenerPorId(Integer id) {
        Resena resena = resenasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));

        return resenaMapper.toResponse(resena);
    }

    public List<ResenaResponse> obtenerPorUsuario(Integer usuarioId) {
        return resenasRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(resenaMapper::toResponse)
                .toList();
    }

    public List<ResenaResponse> obtenerPorVideojuego(Integer videojuegoId) {
        return resenasRepository.findByVideojuegoId(videojuegoId)
                .stream()
                .map(resenaMapper::toResponse)
                .toList();
    }

    public ResenaResponse guardar(ResenaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        Videojuego videojuego = videojuegoRepository.findById(request.getVideojuegoId())
                .orElseThrow(() -> new RuntimeException("Videojuego no existe"));

        Resena resenaGuardada = resenasRepository
                .findByUsuarioIdAndVideojuegoId(
                        request.getUsuarioId(),
                        request.getVideojuegoId())
                .map(resenaExistente -> {

                    resenaExistente.setCalificacion(request.getCalificacion());
                    resenaExistente.setComentario(request.getComentario());

                    return resenasRepository.save(resenaExistente);

                })
                .orElseGet(() -> {

                    Resena nueva = Resena.builder()
                            .usuario(usuario)
                            .videojuego(videojuego)
                            .calificacion(request.getCalificacion())
                            .comentario(request.getComentario())
                            .build();

                    return resenasRepository.save(nueva);
                });

        return resenaMapper.toResponse(resenaGuardada);
    }

    public void eliminar(Integer id) {

        if (!resenasRepository.existsById(id)) {
            throw new RuntimeException("Reseña no encontrada");
        }

        resenasRepository.deleteById(id);
    }
}