package cl.triskeledu.resenas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.resenas.dto.ResenaRequest;
import cl.triskeledu.resenas.model.Resena;
import cl.triskeledu.resenas.model.Usuario;
import cl.triskeledu.resenas.model.Videojuego;
import cl.triskeledu.resenas.repository.ResenasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenasRepository resenasRepository;

    public List<Resena> obtenerTodas() {
        return resenasRepository.findAll();
    }

    public Resena obtenerPorId(Integer id) {
        return resenasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
    }

    public List<Resena> obtenerPorUsuario(Integer usuarioId) {
        return resenasRepository.findByUsuarioId(usuarioId);
    }

    public List<Resena> obtenerPorVideojuego(Integer videojuegoId) {
        return resenasRepository.findByVideojuegoId(videojuegoId);
    }

    public Resena guardar(ResenaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        Videojuego videojuego = videojuegoRepository.findById(request.getVideojuegoId())
                .orElseThrow(() -> new RuntimeException("Videojuego no existe"));

        return resenasRepository
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
    }

    public void eliminar(Integer id) {

        if (!resenasRepository.existsById(id)) {
            throw new RuntimeException("Reseña no encontrada");
        }

        resenasRepository.deleteById(id);
    }
}