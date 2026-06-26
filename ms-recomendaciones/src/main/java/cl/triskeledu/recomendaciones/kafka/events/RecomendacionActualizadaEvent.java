package cl.triskeledu.recomendaciones.kafka.events;

import java.math.BigDecimal;

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
public class RecomendacionActualizadaEvent {

    private Integer recomendacionId;

    private BigDecimal afinidadAnterior;

    private BigDecimal afinidadNueva;

}
