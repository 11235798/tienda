package cl.triskeledu.resenas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votos_resena")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VotoResena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resena_id")
    private Resena resena;

    @Column(name = "voto_util")
    private Boolean votoUtil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VotoResena)) return false;
        VotoResena that = (VotoResena) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}