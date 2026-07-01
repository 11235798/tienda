package cl.triskeledu.usuarios.dto;

import lombok.*; 

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioLogroRequest{
    private Integer usuarioId;
    private Integer logroId;
}