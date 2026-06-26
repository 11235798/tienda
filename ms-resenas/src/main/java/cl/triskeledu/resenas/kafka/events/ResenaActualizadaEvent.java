package cl.triskeledu.resenas.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResenaActualizadaEvent {

    private Integer id;
    private Integer usuarioId;
    private Integer videojuegoId;
    private Integer calificacion;
    private String comentario;
}