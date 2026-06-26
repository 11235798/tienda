package cl.triskeledu.recomendaciones.kafka.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecomendacionEliminadaEvent {

    private Integer recomendacionId;

    private Integer usuarioId;

    private Integer videojuegoId;

}
