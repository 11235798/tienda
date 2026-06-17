package cl.triskeledu.descuentos.dto;

import lombok.Data;

@Data
public class VideojuegoProyeccionResponse {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // // id          SERIAL       PRIMARY KEY,
    // @Column(name = "sku", nullable = false)
    private String sku;
    // // sku         VARCHAR(20)  UNIQUE NOT NULL,
    // @Column(name = "titulo", nullable = false)
    private String titulo;
    // // titulo      VARCHAR(255) NOT NULL,
    // @Column(name = "precio_base", nullable = false)
    private int precioBase;
    // // precio_base INT          NOT NULL CHECK (precio_base >= 0)
}
