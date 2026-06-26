package cl.triskeledu.resenas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "resenas",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"videojuego_id", "usuario_id"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "videojuego_id", nullable = false)
    private Integer videojuegoId;

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "comentario", columnDefinition = "TEXT")
    private String comentario;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Resena)) {
            return false;
        }
        Resena resena = (Resena) o;
        return id != null && id.equals(resena.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
