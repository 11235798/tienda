package cl.triskeledu.inventario.dto;

import java.time.LocalDateTime;

import cl.triskeledu.inventario.model.TipoMovimiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventarioResponse {
    private Long id;
    private String nombre;
    private String ubicacion;
    private LocalDateTime fechaModificacion;
    private Long productoId; // ID del producto del microservicio de catálogo
    private Integer stockActual;
    private Integer stockMinimo;
    private String nombreAlmacen; // Para no obligar al front a buscar el nombre
    private Long almacenId;
    private LocalDateTime fechaCreacion;
    private Long productoInventarioId;
    private Integer cantidad;
    private TipoMovimiento tipoMovimiento;
    private String observaciones;
    private LocalDateTime fechaMovimiento;

}

