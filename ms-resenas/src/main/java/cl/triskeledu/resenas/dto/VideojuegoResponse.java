package cl.triskeledu.resenas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideojuegoResponse {

    private Integer id;

    private String sku;

    private String titulo;

    private String desarrolladora;

    private Integer anioLanzamiento;
}