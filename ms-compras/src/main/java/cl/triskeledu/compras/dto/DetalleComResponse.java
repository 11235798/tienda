package cl.triskeledu.compras.dto;

import lombok.Data;

@Data
public class DetalleComResponse {
    private Long id;
    private Long clienteId;
    private String videojuegoSku;
    private int cantidad;
    private int precioHistorico;
    private LocalDateTime fechaCompra;
}
