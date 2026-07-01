package cl.triskeledu.usuarios.dto;

import lombok.*; 

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioRequest{
    private String nickname;
    private String email;
    private Integer nivelCuenta;
}