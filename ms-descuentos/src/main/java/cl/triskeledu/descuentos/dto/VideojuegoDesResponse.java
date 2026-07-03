package cl.triskeledu.descuentos.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VideojuegoDesResponse extends RepresentationModel<VideojuegoDesResponse> {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // // id              SERIAL      PRIMARY KEY,
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", nullable = false)
    private String videojuegoSku;
    // // videojuego_id   INT         NOT NULL REFERENCES videojuegos(id) ON DELETE CASCADE,
    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", nullable = false)
    private Long campanaId;
    // // campana_id      INT         NOT NULL REFERENCES campanas_descuento(id) ON DELETE CASCADE,
    // @Column(name = "estado")
    private String estado;
    // // estado          VARCHAR(20) DEFAULT 'Activo' CHECK (estado IN ('Activo', 'Pausado')),
    // // UNIQUE (videojuego_id, campana_id)
}
