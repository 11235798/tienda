package cl.triskeledu.descuentos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class DescuentoRequest {


    @NotBlank(message = "El código del cupón es obligatorio")
    @Size(max = 20, message = "El código del cupón no puede superar los 20 caracteres")
    private String codigoCupon;

    @NotNull(message = "El valor del descuento es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El valor del descuento debe ser mayor a 0")
    private BigDecimal valor;

    @NotNull(message = "El tipo de descuento es obligatorio")
    private String tipoDescuento; // Enum como String para el DTO

    @NotNull(message = "El ID de la campaña es obligatorio")
    private Long campaniaId; // Referencia a la campaña solo por ID

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;

    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha de creación no puede ser futura")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "La fecha de modificación es obligatoria")
    @PastOrPresent(message = "La fecha de modificación no puede ser futura")
    private LocalDateTime fechaModificacion;

}

