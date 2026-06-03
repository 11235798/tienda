package cl.triskeledu.catalogo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "videojuego_categoria",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_videojuego_categoria_videojuego_categoria",
        columnNames = {
            "videojuego_id",
            "categoria_id"
        })
    },
    indexes = {
        @Index(name = "idx_videojuego_categoria_videojuego",
        columnList = "videojuego_id"),
        @Index(name = "idx_videojuego_categoria_categoria",
        columnList = "categoria_id")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideojuegoCategoriaCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idVC;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "videojuego_id", nullable = false)
    private VideojuegoCatalogo videojuegoVC;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaCatalogo categoriaVC;
}
