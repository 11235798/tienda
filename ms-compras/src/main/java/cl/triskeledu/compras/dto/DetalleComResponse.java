package cl.triskeledu.compras.dto;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DetalleComResponse extends RepresentationModel<DetalleComResponse>{
    private Long id;
    private String clienteRut;
    private String videojuegoSku;
    private int cantidad;
    private int precioHistorico;
    private LocalDateTime fechaCompra;
}
