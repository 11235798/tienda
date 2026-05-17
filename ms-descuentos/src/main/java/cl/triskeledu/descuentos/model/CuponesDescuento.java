package cl.triskeledu.descuentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "cupones",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_cupones_codigo", columnNames = "codigo")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuponesDescuento {
    @Column (name = "codigo", length = 20)
    private String codigo;

    @Column(name = "porcentaje_dcto")
    private int porcentajeDcto;

    @Column(name = "activo")
    private boolean activo;
}
