package cl.triskeledu.descuentos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DescuentosResponse {
    private Long id;
    private String codigoCupon;
    private BigDecimal valor;
    private String tipoDescuento; // Enum como String
    private Long campaniaId;      // Referencia solo por ID
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

}
