package cl.triskeledu.catalogo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoRepsonse {
    
    private Date fecha_modificacion;
    private String nombre;
    private String despcripcion;
    private String categoria;
    private Date fecha_creacion;
    
}
