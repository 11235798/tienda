package cl.triskeledu.recomendaciones.dto;

import java.time.LocalDateTime;

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
public class RecomendacionResponse {
    private Long id;
    private String categoriaInteres;
    private Integer nivelInteres;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private Long productoId;
    private Double scoreRelevancia;
    private String algoritmoVersion;
    private EstadoRecomendacion estado;
    private LocalDateTime fechaGeneracion;
    private Long usuarioId;

}
