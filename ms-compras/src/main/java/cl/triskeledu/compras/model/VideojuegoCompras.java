package cl.triskeledu.compras.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "videojuegos",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_videojuegos_sku", columnNames = "sku")
    },
    indexes = {
        @Index(name = "idx_videojuegos_sku", columnList = "sku")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideojuegoCompras {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sku", length = 20, unique = true, nullabe = false)
    private String sku;

    @Column(name = "titulo", length = 255, nullable = false)
    private String titulo;

    @Column(name = "formato", length = 50, nullable = false)
    private String formato;//CHECK (formato IN ('Físico', 'Digital'))

    @Column(name = "precio_actual", nullable = false)
    private int precioActual;//CHECK (precio_actual >= 0)
}
