package cl.triskeledu.resenas.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.triskeledu.resenas.dto.ResenaResponse;

@Repository
public class ResenasRepository {
private List<ResenaResponse> resenas = new ArrayList<>();
// Obtener todos los registros
    public List<ResenaResponse> findAll() {
        return List.copyOf(resenas);
    }

    // Buscar por ID
    public ResenaResponse findById(Integer id) {
    return resenas.stream()
            .filter(o -> o.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Buscar por Email del usuario
    public List<ResenaResponse> findByUsuarioEmail(Long userEmail) {
        return resenas.stream()
            .filter(o -> o.getUserEmail() != null && o.getUserEmail().equals(userEmail))
            .toList();
    }

    public ResenaResponse save(ResenaResponse resena) {
        // CREAR
        if (resena.getId() == 0) {
            int lastId = resenas.size() + 1;
            resena.setId(lastId);
            resenas.add(resena);
            return resena;
        }

        // ACTUALIZAR
        ResenaResponse resenaEncontrada = findById(resena.getId());

        if (resenaEncontrada == null) {
            return null;
        }

        resenaEncontrada.setUserEmail(resena.getUserEmail());
        resenaEncontrada.setPuntos(resena.getPuntos());
        resenaEncontrada.setVoto(resena.getVoto());
        resenaEncontrada.setComentario(resena.getComentario());
        resenaEncontrada.setTitulo(resena.getTitulo());

        return resenaEncontrada;
    }
    public Boolean deleteById(Integer id) {
        return resenas.removeIf(resena -> resena.getId() == id);
    }
}