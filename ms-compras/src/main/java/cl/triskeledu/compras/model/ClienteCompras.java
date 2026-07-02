package cl.triskeledu.compras.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "clientes",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_clientes_rut", columnNames = "rut"),
        @UniqueConstraint(name = "uk_clientes_email", columnNames = "email")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rut", length = 15, unique = true, nullable = false)
    private String rut;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "email", length = 150, unique = true, nullable = false)
    private String email;

    //rol                 VARCHAR(50)     NOT NULL
    @Column(name = "rol", length = 50, nullable = false)
    private String rol;
}
