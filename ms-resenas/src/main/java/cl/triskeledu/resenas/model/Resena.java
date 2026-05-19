package cl.triskeledu.resenas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "resenas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "juego_sku")
    private JuegoProyeccion juego;

    @Column(name = "user_email", length = 100)
    private String userEmail;

    @Column(name = "puntos")
    private Integer puntos;

    @Lob
    @Column(name = "comentario")
    private String comentario;

    @OneToMany(mappedBy = "resena", cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    private List<VotoResena> votos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resena)) return false;
        Resena that = (Resena) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}