package cl.triskeledu.catalogo.dto;

import lombok.Data;

@Data
public class VideojuegoCatResponse {
    private Long idVid;
    private String skuVid;
    private String tituloVid;
    private String desarrolladoraVid;
    private int anioLanzamientoVid;
    private String plataformaVid;
}
