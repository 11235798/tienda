package cl.triskeledu.descuentos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "videojuego_descuento",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_videojuego_descuento_videojuego_campana",
        columnNames = {"videojuego_sku",
            "campana_id"})
    },
    indexes = {
        @Index(name = "idx_descuentos_videojuego",
        columnList = "videojuego_sku"),
        @Index(name = "idx_descuentos_campana",
        columnList = "campana_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku", nullable = false)
    private VideojuegoProyeccion videojuego;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campana_id", nullable = false)
    private CampanaDescuento campana;

    @Column(name = "estado")
    private String estado;

}
