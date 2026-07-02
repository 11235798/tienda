package cl.triskeledu.usuarios.dto;

import lombok.*; 

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioResponse{
    private Long id;
    private String nickname;
    private String email;
    private String rol;
    private Boolean activo;
}