package cl.triskeledu.notificaciones.repository;

import java.util.ArrayList;
import java.util.List;

import cl.triskeledu.notificaciones.dto.NotificasionesResponse;


public class NotificacionesRepository {
private List<NotificasionesResponse> notificaciones = new ArrayList<>();

    // Obtener todos los registros
    public List<NotificasionesResponse> findAll() {
        return notificaciones;
    }

    // Buscar por ID usando Streams de Java 8+
    public NotificasionesResponse findById(Long id) {
        return notificaciones.stream()
            .filter(o -> o.getId().equals(id)) // Se usa .equals() para comparar objetos Long
            .findFirst()
            .orElse(null);
    }

    // Buscar por ID de plantilla (Reemplazo lógico para productoId)
    public List<NotificasionesResponse> findByPlantillaId(Long plantillaId) {
        return notificaciones.stream()
            .filter(o -> o.getPlantillaId().equals(plantillaId))
            .toList();
    }

    // Buscar por destinatario (Reemplazo lógico para almacenId)
    public List<NotificasionesResponse> findByDestinatario(String destinatario) {
        return notificaciones.stream()
            .filter(o -> o.getDestinatario() != null && o.getDestinatario().equalsIgnoreCase(destinatario))
            .toList();
    }

    // Guardar o actualizar
    public NotificasionesResponse save(NotificasionesResponse item) {

        // CREAR (Se verifica si es null o 0 para mayor seguridad)
        if (item.getId() == null || item.getId() == 0) {
            Long lastId = (long) (notificaciones.size() + 1);
            item.setId(lastId);
            notificaciones.add(item);
            return item;
        }

        // ACTUALIZAR
        NotificasionesResponse itemEncontrado = findById(item.getId());

        if (itemEncontrado == null) {
            return null;
        }

        // Se actualizan todos los campos correspondientes a la notificación
        itemEncontrado.setCodigo(item.getCodigo());
        itemEncontrado.setAsunto(item.getAsunto());
        itemEncontrado.setContenidoBase(item.getContenidoBase());
        itemEncontrado.setFechaCreacion(item.getFechaCreacion());
        itemEncontrado.setFechaModificacion(item.getFechaModificacion());
        itemEncontrado.setDestinatario(item.getDestinatario());
        itemEncontrado.setCanal(item.getCanal());
        itemEncontrado.setMensajeFinal(item.getMensajeFinal());
        itemEncontrado.setEnviado(item.getEnviado());
        itemEncontrado.setPlantillaId(item.getPlantillaId());
        itemEncontrado.setCodigoPlantilla(item.getCodigoPlantilla());
        itemEncontrado.setFechaEnvio(item.getFechaEnvio());

        return itemEncontrado;
    }

    // Eliminar
    public Boolean deleteById(Long id) {
        return notificaciones.removeIf(item -> item.getId().equals(id));
    }
}
