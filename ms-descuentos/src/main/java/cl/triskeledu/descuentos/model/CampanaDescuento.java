package cl.triskeledu.descuentos.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "campanas_descuento",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_campanas_descuento_codigo_promocion",
        columnNames = "usuario_email")
    },
    indexes = {
        @Index(name = "idx_campana_fecha_inicio",
        columnList = "fecha_inicio")
        //idx_campanas_fechas ON campanas_descuento(fecha_inicio, fecha_fin);
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampanaDescuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // id                  SERIAL          PRIMARY KEY,
    @Column(name = "codigo_promocion", nullable = false)
    private String codigoPromocion;
    // codigo_promocion    VARCHAR(50)     UNIQUE NOT NULL,
    @Column(name = "nombre_campana", nullable = false)
    private String nombreCampana;
    // nombre_campana      VARCHAR(100)    NOT NULL,
    @Column(name = "porcentaje_rebaja", nullable = false)
    private int porcentajeRebaja;
    // porcentaje_rebaja   INT             NOT NULL CHECK (porcentaje_rebaja > 0 AND porcentaje_rebaja <= 100),
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;
    // fecha_inicio        TIMESTAMP       NOT NULL,
    @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;
    // fecha_fin           TIMESTAMP       NOT NULL,
    // CHECK (fecha_fin > fecha_inicio)
}
