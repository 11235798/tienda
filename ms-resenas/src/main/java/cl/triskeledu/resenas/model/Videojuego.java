package cl.triskeledu.resenas.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videojuegos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sku", nullable = false, length = 20, unique = true)
    private String sku;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "desarrolladora", nullable = false, length = 100)
    private String desarrolladora;

    @Column(name = "anio_lanzamiento", nullable = false)
    private Integer anioLanzamiento;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Resena> resenas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Videojuego)) {
            return false;
        }
        Videojuego videojuego = (Videojuego) o;
        return id != null && id.equals(videojuego.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
