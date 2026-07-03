package cl.triskeledu.resenas.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ResenaResponse extends RepresentationModel<ResenaResponse> {

    private Integer id;

    private Integer videojuegoId;
    private String videojuegoTitulo;

    private Integer usuarioId;
    private String usuarioNickname;

    private Integer calificacion;

    private String comentario;
}
