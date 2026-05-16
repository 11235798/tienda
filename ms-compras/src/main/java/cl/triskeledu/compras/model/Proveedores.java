package cl.triskeledu.compras.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "proveedores",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_proveedores_rut", columnNames = "rut")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedores {
    @Column(name = "rut", length = 20)
    private String rut;
    
    @Column(name = "razon_social", length = 150, nullable = false)
    private String razonSocial;
    
    @Column(name = "contacto", length = 100)
    private String contacto;
}
