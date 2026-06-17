package cl.triskeledu.descuentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "videojuego_descuento",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_videojuego_descuento_videojuego_campana",
        columnNames = {"videojuego_id",
            "campana_id"})
    },
    indexes = {
        @Index(name = "idx_descuentos_videojuego",
        columnList = "videojuego_id"),
        @Index(name = "idx_descuentos_campana",
        columnList = "campana_id")
// idx_descuentos_videojuego ON videojuego_descuento(videojuego_id);
// idx_descuentos_campana ON videojuego_descuento(campana_id);
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideojuegoDescuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // id              SERIAL      PRIMARY KEY,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private VideojuegoProyeccion videojuegoId;
    // videojuego_id   INT         NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private CampanaDescuento campanaId;
    // campana_id      INT         NOT NULL REFERENCES campanas_descuento(id) ON DELETE CASCADE,
    @Column(name = "estado")
    private String estado;
    // estado          VARCHAR(20) DEFAULT 'Activo' CHECK (estado IN ('Activo', 'Pausado')),
    // UNIQUE (videojuego_id, campana_id)
}
