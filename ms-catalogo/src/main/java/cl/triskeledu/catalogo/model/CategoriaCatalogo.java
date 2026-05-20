package cl.triskeledu.catalogo.model;

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
    @Column(name = "id")
    private Long idCategoria;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombreCategoria;
}
