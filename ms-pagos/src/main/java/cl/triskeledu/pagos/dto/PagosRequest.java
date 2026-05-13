package cl.triskeledu.pagos.dto;

import java.math.BigDecimal;

import cl.triskeledu.pagos.modelo.EstadoTransaccion;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PagosRequest {
    @NotBlank(message = "El número de serie es obligatorio")
    @Size(max = 50, message = "El número de serie no puede exceder los 50 caracteres")
    private String numeroSerie;


    @NotNull(message = "El ID de la transacción asociada es obligatorio")
    private Long transaccionId;
    @NotBlank(message = "La referencia externa es obligatoria")
    @Size(max = 100, message = "La referencia no puede exceder los 100 caracteres")
    private String referenciaExterna;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal monto;

    @NotBlank(message = "La moneda es obligatoria (ej: CLP, USD)")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres (ISO)")
    private String moneda;

    @NotNull(message = "El estado de la transacción es obligatorio")
    private EstadoTransaccion estado;

    @NotNull(message = "El ID del método de pago es obligatorio")
    private Long metodoPagoId;@NotBlank(message = "El nombre del método de pago es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    private String nombre;

    @Size(max = 200, message = "La descripción no puede exceder los 200 caracteres")
    private String descripcion;

    @NotNull(message = "El estado activo/inactivo es obligatorio")
    private Boolean activo;
}

