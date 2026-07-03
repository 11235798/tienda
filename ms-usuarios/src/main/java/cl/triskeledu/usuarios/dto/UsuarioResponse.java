package cl.triskeledu.usuarios.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.*; 

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioResponse extends RepresentationModel<UsuarioResponse> {
    private Long id;
    private String nickname;
    private String email;
    private String rol;
    private Boolean activo;
}