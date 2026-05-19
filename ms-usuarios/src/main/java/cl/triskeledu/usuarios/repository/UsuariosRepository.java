package cl.triskeledu.usuarios.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.usuarios.dto.UsuariosResenas;

public class UsuariosRepository {
    private List<UsuariosResenas> usuarios = new ArrayList<>();

    // Obtener todos los registros
    public List<UsuariosResenas> findAll() {
        return usuarios;
    }

    // Buscar la primera coincidencia por ID del usuario
    public UsuariosResenas findByUsuarioId(Long usuarioId) {
        return usuarios.stream()
            .filter(o -> o.getUsuarioId() != null && o.getUsuarioId().equals(usuarioId))
            .findFirst()
            .orElse(null);
    }

    // Buscar TODOS los registros asociados a un mismo usuario (por las relaciones 1:N y N:M)
    public List<UsuariosResenas> findAllByUsuarioId(Long usuarioId) {
        return usuarios.stream()
            .filter(o -> o.getUsuarioId() != null && o.getUsuarioId().equals(usuarioId))
            .toList();
    }

    // Buscar por nombre de usuario (Username)
    public UsuariosResenas findByUsername(String username) {
        return usuarios.stream()
            .filter(o -> o.getUsername() != null && o.getUsername().equalsIgnoreCase(username))
            .findFirst()
            .orElse(null);
    }

    // Buscar por Email
    public UsuariosResenas findByEmail(String email) {
        return usuarios.stream()
            .filter(o -> o.getEmail() != null && o.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    // Guardar o actualizar
    public UsuariosResenas save(UsuariosResenas item) {

        // CREAR (Se verifica si es null o 0 para mayor seguridad)
        if (item.getUsuarioId() == null || item.getUsuarioId() == 0) {
            Long lastId = (long) (usuarios.size() + 1);
            item.setUsuarioId(lastId);
            usuarios.add(item);
            return item;
        }

        // ACTUALIZAR (Actualiza la primera fila que encuentre de este usuario)
        UsuariosResenas itemEncontrado = findByUsuarioId(item.getUsuarioId());

        if (itemEncontrado == null) {
            return null;
        }

        // --- Datos de Usuario ---
        itemEncontrado.setUsername(item.getUsername());
        itemEncontrado.setEmail(item.getEmail());
        itemEncontrado.setActivo(item.getActivo());
        itemEncontrado.setFechaCreacion(item.getFechaCreacion());
        itemEncontrado.setFechaModificacion(item.getFechaModificacion());

        // --- Datos de Perfil ---
        itemEncontrado.setNombre(item.getNombre());
        itemEncontrado.setApellido(item.getApellido());
        itemEncontrado.setTelefono(item.getTelefono());
        itemEncontrado.setBiografia(item.getBiografia());

        // --- Datos de Dirección ---
        itemEncontrado.setDireccionId(item.getDireccionId());
        itemEncontrado.setCalle(item.getCalle());
        itemEncontrado.setCiudad(item.getCiudad());
        itemEncontrado.setPais(item.getPais());
        itemEncontrado.setCodigoPostal(item.getCodigoPostal());

        // --- Datos de Rol ---
        itemEncontrado.setRolId(item.getRolId());
        itemEncontrado.setNombreRol(item.getNombreRol());

        return itemEncontrado;
    }

    // Eliminar todos los registros asociados a un ID de usuario
    public Boolean deleteByUsuarioId(Long usuarioId) {
        return usuarios.removeIf(item -> item.getUsuarioId() != null && item.getUsuarioId().equals(usuarioId));
    }
}

