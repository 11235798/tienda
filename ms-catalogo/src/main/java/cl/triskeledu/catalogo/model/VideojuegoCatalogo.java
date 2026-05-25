package cl.triskeledu.catalogo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "videojuegos",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_videojuego_catalogo_sku",
        columnNames = "sku")
    },
    indexes = {
        @Index(name = "idx_videojuegos_sku",
        columnList = "sku"),
        @Index(name = "idx_videojuegos_titulo",
        columnList = "titulo")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideojuegoCatalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idVideojuego;
    
    @Column(name = "sku", length = 20, nullable = false)
    private String skuVideojuego;
    
    @Column(name = "titulo", length = 255, nullable = false)
    private String tituloVideojuego;
    
    @Column(name = "desarrolladora", length = 100, nullable = false)
    private String desarrolladoraVideojuego;
    
    @Column(name = "anio_lanzamiento", nullable = false)
    private int anioLanzamientoVideojuego;
    
    @Column(name = "plataforma", length = 150, nullable = false)
    private String plataformaVideojuego;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "videojuego_categoria", 
        joinColumns = @JoinColumn(name = "videojuego_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaCatalogo> categorias = new ArrayList<>();
}
