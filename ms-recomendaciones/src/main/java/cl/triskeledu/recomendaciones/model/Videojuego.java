package cl.triskeledu.recomendaciones.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "genero_principal", nullable = false, length = 100)
    private String generoPrincipal;

    @Column(name = "plataforma", nullable = false, length = 100)
    private String plataforma;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Recomendacion> recomendaciones = new ArrayList<>();
}
