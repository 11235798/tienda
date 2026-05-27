package cl.triskeledu.resenas.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResenaResponse {

    private Integer id;
    private Long userEmail;
    private Integer puntos;
    private String comentario;
    private Boolean voto;
    private String titulo;
}
