package cl.triskeledu.usuarios.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nickname", nullable=false, length=100, unique=true)
    private String nickname;

    @Column(name="email", nullable=false, length=150, unique=true)
    private String email;

    @Column(name="nivel_cuenta")
    private Integer nivelCuenta;

    @Column(name="password", nullable=false, length=255)
    private String password;

    @Column(name="rol", nullable=false, length=50)
    private String rol;

    @CreatedDate
    @Column(name="fecha_registro", nullable=false)
    private LocalDateTime fechaRegistro;

    @Builder.Default
    @OneToMany(mappedBy="usuario")
    private List<UsuarioLogro> usuarioLogros=new ArrayList<>();

    @Override 
    public boolean equals(Object o){
        if(this==o)return true; if(!(o instanceof Usuario u))return false; return Objects.equals(id,u.id);
    }

    @Override 
    public int hashCode(){
        return Objects.hash(id);
    }
}
