package cl.triskeledu.usuarios.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="usuario_logros",uniqueConstraints=@UniqueConstraint(columnNames={"usuario_id","logro_id"}))
@EntityListeners(AuditingEntityListener.class)
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class UsuarioLogro{
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;

@ManyToOne 
@JoinColumn(name="usuario_id",nullable=false)
private Usuario usuario;

@ManyToOne 
@JoinColumn(name="logro_id",nullable=false)
private Logro logro;

@CreatedDate
@Column(name="fecha_desbloqueo",nullable=false)
private LocalDateTime fechaDesbloqueo;

@Override 
public boolean equals(Object o){
    if(this==o)return true; if(!(o instanceof UsuarioLogro x))return false; return Objects.equals(id,x.id);
}

@Override 
public int hashCode(){
    return Objects.hash(id);
}
}
