package cl.triskeledu.recomendaciones.dto;

import java.math.BigDecimal;

import cl.triskeledu.recomendaciones.model.EstadoRecomendacion;
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
public class RecomendacionRequest {

    private Integer usuarioId;
    private Integer videojuegoId;
    private BigDecimal porcentajeAfinidad;
    private EstadoRecomendacion estado;

}

