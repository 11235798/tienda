package cl.triskeledu.usuarios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="logros")
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logro{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Integer id;

 @Column(name="codigo_logro",nullable=false,length=50,unique=true)
 private String codigoLogro;

 @Column(name="juego_asociado",nullable=false,length=100)
 private String juegoAsociado;

 @Column(nullable=false,length=150)
 private String titulo;

 @Enumerated(EnumType.STRING)
 @Column(nullable=false,length=50)
 private Rareza rareza;

 @Column(name="xp_otorgada",nullable=false)
 private Integer xpOtorgada;

 @Builder.Default
 @OneToMany(mappedBy="logro")
 private List<UsuarioLogro> usuarioLogros=new ArrayList<>();

 @Override 
 public boolean equals(Object o){
    if(this==o)return true; if(!(o instanceof Logro l))return false; return Objects.equals(id,l.id);
}

 @Override 
 public int hashCode(){
    return Objects.hash(id);
}}
