package cl.triskeledu.catalogo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    
    private Long id;
    private Date fecha_modificacion;
    private String nombre;
    private String despcripcion;
    private Date fecha_creacion;
    private String categorias;
    private String descripcionDetallada;
    private BigDecimal precio;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    
}
