package cl.triskeledu.pedidos.dto;

import java.math.BigDecimal;

import cl.triskeledu.pedidos.model.EstadoPedido;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;

public class PedidosRequest {
    @NotNull(message = "El estado es obligatorio")
    private EstadoPedido estado;

    @NotBlank(message = "El código de seguimiento es necesario para actualizar el estado")
    private String codigoSeguimiento;
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private BigDecimal precioUnitario;
    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotBlank(message = "Las notas de entrega no pueden estar vacías")
    private String notasEntrega;


    
}
