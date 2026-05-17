package cl.triskeledu.resenas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "juegos_proyeccion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JuegoProyeccion {

    @Id
    @Column(name = "sku", nullable = false, length = 50)
    private String sku;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    private List<Resena> resenas = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JuegoProyeccion)) return false;
        JuegoProyeccion that = (JuegoProyeccion) o;
        return sku != null && sku.equals(that.sku);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}