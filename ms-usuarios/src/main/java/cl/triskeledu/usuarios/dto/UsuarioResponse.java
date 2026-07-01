package cl.triskeledu.usuarios.dto;

import lombok.*; 
import java.time.*;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class UsuarioResponse{
    private Integer id;
    private String nickname;
    private String email;
    private Integer nivelCuenta;
    private LocalDateTime fechaRegistro;
}