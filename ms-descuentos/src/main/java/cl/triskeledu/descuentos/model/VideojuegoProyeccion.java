package cl.triskeledu.descuentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "videojuegos",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_videojuegos_sku", columnNames = "sku")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideojuegoProyeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // id          SERIAL       PRIMARY KEY,
    @Column(name = "sku", nullable = false)
    private String sku;
    // sku         VARCHAR(20)  UNIQUE NOT NULL,
    @Column(name = "titulo", nullable = false)
    private String titulo;
    // titulo      VARCHAR(255) NOT NULL,
    @Column(name = "precio_base", nullable = false)
    private int precioBase;
    // precio_base INT          NOT NULL CHECK (precio_base >= 0)
}
