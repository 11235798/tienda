package cl.triskeledu.usuarios.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.triskeledu.usuarios.dto.UsuarioRequest;
import cl.triskeledu.usuarios.dto.UsuarioResponse;
import cl.triskeledu.common.exception.*;
import cl.triskeledu.usuarios.mapper.UsuarioMapper;
import cl.triskeledu.usuarios.model.Usuario;
import cl.triskeledu.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    //El service lo coordina todo, aquí están la programación y las reglas de negocio del microservicio

    public List<UsuarioResponse> findAll() {
        //Lista todos los usuarios
        return usuarioMapper.toResponseList(usuarioRepository.findAll());
    }

    public UsuarioResponse findById(long id) {
        //Busca a un usuario según la id entregada
        return usuarioMapper.toResponse(getUsuarioById(id));
    }

    public UsuarioResponse findByEmail(String email) {
        //Busca a un usuario según el email entregado
        return usuarioMapper.toResponse(getUsuarioByEmail(email));
    }

    @Transactional
    public UsuarioResponse create(UsuarioRequest request) {
        //Crea a un usuario nuevo
        validateEmailUnico(request.getEmail());
        Usuario usuario = new Usuario();
        usuarioMapper.updateEntity(request, usuario);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setActivo(true);
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponse update(long id, UsuarioRequest request) {
        //Actualiza la cuenta del usuario de la id proporcionada
        Usuario usuario = getUsuarioById(id);
        if (!usuario.getEmail().equalsIgnoreCase(request.getEmail())) {
            validateEmailUnico(request.getEmail());
        }
        usuarioMapper.updateEntity(request, usuario);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public void deleteById(long id) {
        //Elimina al usuario de la id proporcionada
        Usuario usuario = getUsuarioById(id);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }

    @Transactional
    public UsuarioResponse activar(long id) {
        //Activa la cuenta de un usuario si está desactivada
        Usuario usuario = getUsuarioById(id);
        usuario.setActivo(true);
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponse desactivar(long id) {
        //Desactiva la cuenta de un usuario si esta activa
        Usuario usuario = getUsuarioById(id);
        usuario.setActivo(false);
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    //----------------------------Estas son Funciones Privadas!!!------------------------------------

    private Usuario getUsuarioById(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuarios", "ID", id));
    }

    private Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuarios", "email", email));
    }

    private void validateEmailUnico(String email) {
        usuarioRepository.findByEmail(email).ifPresent(u -> {
            throw new DuplicateResourceException(
                "Un Usuario", "email", email,
                u.getNickname()
            );
        });
    }
}
