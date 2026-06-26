package cl.triskeledu.recomendaciones.dto;

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
public class VideojuegoResponse {

    private Integer id;
    private String sku;
    private String titulo;
    private String generoPrincipal;
    private String plataforma;

}