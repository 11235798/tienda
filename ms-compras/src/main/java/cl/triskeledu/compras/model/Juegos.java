package cl.triskeledu.compras.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "juegos_proyeccion",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_juegos_proyeccion_sku", columnNames = "sku")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Juegos {
    @Column(name = "sku", length = 50)
    private String sku;
    
    @Column(name = "titulo", length = 150)
    private String titulo;
}
