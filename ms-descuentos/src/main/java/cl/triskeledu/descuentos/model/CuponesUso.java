package cl.triskeledu.descuentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "cupones_uso"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuponesUso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cupon_codigo", referencedColumnName = "codigo", nullable = false)
    private CuponesDescuento cuponCodigo;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aplicado_a_sku", referencedColumnName = "sku", nullable = false)
    private JuegosDescuento aplicadoASku;
}
