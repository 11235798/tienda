package cl.triskeledu.catalogo.dto;

import lombok.Data;

@Data
public class VideojuegoCatResponse {
    private Long idVideojuego;
    private String skuVideojuego;
    private String tituloVideojuego;
    private String desarrolladoraVideojuego;
    private int anioLanzamientoVideojuego;
    private String plataformaVideojuego;
}
