package cl.triskeledu.compras.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "ordenes_compra"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenesCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proveedor_rut", referencedColumnName = "rut", nullable = false)
    private Proveedores proveedorRut;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "juego_sku", referencedColumnName = "sku", nullable = false)
    private Juegos juegoSku;
    
    @Column(name = "costo_unitario")
    private int costoUnitario;
    
    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;
}
