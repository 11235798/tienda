package cl.triskeledu.resenas.dto;

import java.time.LocalDateTime;

import cl.triskeledu.resenas.model.TipoReaccion;
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
public class ResenasResponse {

    private Long id;
    private Long productoId;
    private Long usuarioId;
    private String titulo;
    private String comentario;
    private Integer calificacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private String texto;
    private TipoReaccion tipoReaccion;
}
