package cl.triskeledu.descuentos.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CampanaDesResponse {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // // id                  SERIAL          PRIMARY KEY,
    // @Column(name = "codigo_promocion", nullable = false)
    private String codigoPromocion;
    // // codigo_promocion    VARCHAR(50)     UNIQUE NOT NULL,
    // @Column(name = "nombre_campana", nullable = false)
    private String nombreCampana;
    // // nombre_campana      VARCHAR(100)    NOT NULL,
    // @Column(name = "porcentaje_rebaja", nullable = false)
    private int porcentajeRebaja;
    // // porcentaje_rebaja   INT             NOT NULL CHECK (porcentaje_rebaja > 0 AND porcentaje_rebaja <= 100),
    // @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;
    // // fecha_inicio        TIMESTAMP       NOT NULL,
    // @Column(name = "fecha_fin", nullable = false)
    private LocalDateTime fechaFin;
    // // fecha_fin           TIMESTAMP       NOT NULL,
    // // CHECK (fecha_fin > fecha_inicio)
}
