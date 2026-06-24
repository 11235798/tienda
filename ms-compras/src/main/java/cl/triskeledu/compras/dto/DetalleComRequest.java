package cl.triskeledu.compras.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DetalleComRequest {
    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "cliente_id", nullable = false)
    @NotBlank(message = "El email del cliente es obligatorio")
    @Size(min = 1, max = 15, message = "El rut del cliente debe tener entre 1 y 15 caracteres")
    private String clienteRut;

    // @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "videojuego_sku", nullable = false)
    @NotBlank(message = "El sku del videojuego es obligatorio")
    @Size(min = 1, max = 150, message = "El sku del videojuego debe tener entre 1 y 150 caracteres")
    private String videojuegoSku;

    // @Column(name = "cantidad", nullable = false)
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private int cantidad;//CHECK (cantidad > 0),
    
    // @Column(name = "precio_historico", nullable = false)
    @NotBlank(message = "El precio histórico es obligatorio")
    @Min(value = 0, message = "El precio histórico debe ser mayor o igual a 0")
    private int precioHistorico;//CHECK (precio_historico >= 0), -- Congela el precio al momento de comprar
}
