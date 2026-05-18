package cl.triskeledu.notificaciones.modelo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "destinatario", nullable = false, length = 150)
    private String destinatario;

    @Enumerated(EnumType.STRING)
    @Column(name = "canal", nullable = false)
    private CanalNotificacion canal;

    @Lob
    @Column(name = "mensaje_final", nullable = false)
    private String mensajeFinal;

    @Column(name = "enviado", nullable = false)
    private Boolean enviado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantilla_id")
    private Plantilla plantilla;

    @Transient
    private String metadataTemporal;

    @CreatedDate
    @Column(name = "fecha_envio", nullable = false, updatable = false)
    private LocalDateTime fechaEnvio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificacion that = (Notificacion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
