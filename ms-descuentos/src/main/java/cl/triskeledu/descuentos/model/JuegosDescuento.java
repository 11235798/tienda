package cl.triskeledu.descuentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "juegos_proyeccion",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_juegos_proyeccion_sku", columnNames = "sku")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JuegosDescuento {
    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "precio_base")
    private int precioBase;
}
