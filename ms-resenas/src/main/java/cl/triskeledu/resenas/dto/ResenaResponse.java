package cl.triskeledu.resenas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenaResponse {

    private Integer id;

    private Integer videojuegoId;
    private String videojuegoTitulo;

    private Integer usuarioId;
    private String usuarioNickname;

    private Integer calificacion;

    private String comentario;
}
