package cl.triskeledu.usuarios.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.*; 

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {
    //El response son los datos que se le devuelven al cliente como respuesta
    private Long id;
    private String nickname;
    private String email;
    private String rol;
    private Boolean activo;
}