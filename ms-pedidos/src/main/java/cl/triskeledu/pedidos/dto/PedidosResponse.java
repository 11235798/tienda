package cl.triskeledu.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidosResponse {
private Long id;
    private Long clienteId;
    private String codigoSeguimiento;
    private String estado;
    private String notasEntrega;
    private LocalDateTime fechaCreacion;
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
        
}
