package cl.triskeledu.compras.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "detalle_compras",
    indexes = {
        @Index(name = "idx_compras_videojuego", columnList = "videojuego_id"),
        @Index(name = "idx_compras_cliente", columnList = "cliente_id"),
        @Index(name = "idx_compras_fecha", columnList = "fecha_compra")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Long clienteId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "videojuego_sku", nullable = false)
    private String videojuegoSku;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;//CHECK (cantidad > 0),
    
    @Column(name = "precio_historico", nullable = false)
    private int precioHistorico;//CHECK (precio_historico >= 0), -- Congela el precio al momento de comprar
    
    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;//DEFAULT CURRENT_TIMESTAMP -> item.setFechaCreacion(LocalDateTime.now()); en el service (save o create)
}
