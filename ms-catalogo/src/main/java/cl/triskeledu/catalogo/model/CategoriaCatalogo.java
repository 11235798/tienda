package cl.triskeledu.catalogo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "categorias",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_categoria_nombre",
        columnNames = "nombre")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", length = 100, unique = true, nullable = false)
    private String nombreCat;

    @Builder.Default
    @ManyToMany(mappedBy = "categorias")
    private List<VideojuegoCatalogo> videojuegos = new ArrayList<>();
}
