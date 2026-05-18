package cl.triskeledu.inventario.dto;

import cl.triskeledu.inventario.model.TipoMovimiento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InventarioRequest {
@NotNull(message = "El ID de la relación Producto-Inventario es obligatorio")
    private Long productoInventarioId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidad;

    @NotNull(message = "El tipo de movimiento (ENTRADA/SALIDA) es obligatorio")
    private TipoMovimiento tipoMovimiento;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "El ID del almacén es obligatorio")
    private Long almacenId;

    @NotNull(message = "El stock inicial es obligatorio")
    @Min(value = 0, message = "El stock inicial no puede ser negativo")
    private Integer stockActual;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;
    @NotBlank(message = "El nombre del almacén es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;

    @Size(max = 255, message = "La ubicación no puede exceder los 255 caracteres")
    private String ubicacion;
}
