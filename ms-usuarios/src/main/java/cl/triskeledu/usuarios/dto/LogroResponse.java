package cl.triskeledu.usuarios.dto;

import lombok.*; 
import cl.triskeledu.usuarios.model.Rareza; 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class LogroResponse{
    private Integer id;
    private String codigoLogro;
    private String juegoAsociado;
    private String titulo;
    private Rareza rareza;
    private Integer xpOtorgada;
}