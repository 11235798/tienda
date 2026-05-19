package cl.triskeledu.compras.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComprasResponse {

    private long id;
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private Long ordenCompraId; // Referencia a la orden de compra
    private BigDecimal subtotal;
    private BigDecimal detalledesicimal_total;
    
}
