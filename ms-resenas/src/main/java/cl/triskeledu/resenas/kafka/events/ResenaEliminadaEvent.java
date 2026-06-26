package cl.triskeledu.resenas.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResenaEliminadaEvent {

    private Integer resenaId;
    private Integer videojuegoId;
    private Integer usuarioId;
}