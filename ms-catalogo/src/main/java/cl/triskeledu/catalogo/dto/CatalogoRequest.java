package cl.triskeledu.catalogo.dto;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import cl.triskeledu.catalogo.model.EstadoProducto;
public class CatalogoRequest {



    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "La fecha de creación es obligatoria")
    @PastOrPresent(message = "La fecha de creación no puede ser una fecha futura")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "La fecha de modificación es obligatoria")
    @PastOrPresent(message = "La fecha de modificación no puede ser una fecha futura")
    private LocalDateTime fchaModificacion;

    @NotBlank(message = "El SKU es obligatorio")
    @Size(max = 50, message = "El SKU no puede exceder los 50 caracteres")
    String sku;
        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
        @Digits(integer = 8, fraction = 2, message = "Formato de precio inválido")
        BigDecimal precio;

        @NotNull(message = "El estado es obligatorio")
        EstadoProducto estado;

        @NotNull(message = "El ID de la categoría es obligatorio")
        @Positive(message = "El ID de la categoría debe ser un número positivo")
        Long categoriaId;

}
