package cl.triskeledu.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.triskeledu.usuarios.dto.UsuarioRequest;
import cl.triskeledu.usuarios.dto.UsuarioResponse;
import cl.triskeledu.usuarios.model.Usuario;
import cl.triskeledu.usuarios.mapper.UsuarioMapper;
import cl.triskeledu.usuarios.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuariosService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public List<UsuarioResponse> listarUsuarios() {
        log.debug("Iniciando búsqueda de todos los usuarios.");
        return usuarioMapper.toResponseList(usuarioRepository.findAll());

    }

    public UsuarioResponse buscarPorId(Integer id) {
        log.debug("Iniciando búsqueda del usuario con id '{}'.",id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        return usuarioMapper.toResponse(usuario);

    }

    public UsuarioResponse buscarPorEmail(String email) {
        log.debug("Iniciando búsqueda del usuario con email '{}'.",email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        return usuarioMapper.toResponse(usuario);

    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        log.debug("Iniciando creación de nuevo usuario.");
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe.");
        }

        if (usuarioRepository.existsByNickname(request.getNickname())) {
            throw new RuntimeException("El nickname ya existe.");
        }

        Usuario usuario = usuarioMapper.toEntity(request);

        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuario);

    }

    public UsuarioResponse actualizarUsuario(Integer id, UsuarioRequest request) {
        log.debug("Actualizando información del usuario con id '{}'.",id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        usuario.setNickname(request.getNickname());
        usuario.setNivelCuenta(request.getNivelCuenta());

        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toResponse(usuario);

    }

    public void eliminarUsuario(Integer id) {
        log.debug("Iniciando eliminación del usuario con id '{}'.",id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado."));

        usuarioRepository.delete(usuario);

    }

}