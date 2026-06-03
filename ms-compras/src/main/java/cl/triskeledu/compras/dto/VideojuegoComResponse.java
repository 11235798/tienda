package cl.triskeledu.compras.dto;

import lombok.Data;

@Data
public class VideojuegoComResponse {
    private Long id;
    private String sku;
    private String titulo;
    private String formato;
    private int precioActual;
}
