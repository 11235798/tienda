package cl.triskeledu.pagos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.triskeledu.pagos.modelo.EstadoTransaccion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagosResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private String referenciaExterna;
    private BigDecimal monto;
    private String moneda;
    private EstadoTransaccion estado;
    private Long metodoPagoId;
    private String nombreMetodoPago; // Facilita la visualización en tablas
    private LocalDateTime fechaTransaccion;
    private String numeroSerie;
    private String datosXml;
    private Long transaccionId;
    private LocalDateTime fechaEmision;
}
