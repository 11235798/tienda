package cl.triskeledu.usuarios.dto;

import lombok.*; 
import java.time.*; 

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioLogroResponse{
    private Integer id;
    private Integer usuarioId;
    private Integer logroId;
    private LocalDateTime fechaDesbloqueo;
}