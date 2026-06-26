package cl.triskeledu.recomendaciones.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "recomendaciones",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_recomendacion_usuario_videojuego", columnNames = {"usuario_id", "videojuego_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "videojuego_id", nullable = false)
    private Integer videojuegoId;

    @Column(name = "porcentaje_afinidad", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeAfinidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoRecomendacion estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recomendacion)) return false;
        Recomendacion that = (Recomendacion) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
