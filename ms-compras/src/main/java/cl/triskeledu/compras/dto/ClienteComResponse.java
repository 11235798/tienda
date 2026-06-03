package cl.triskeledu.compras.dto;

import lombok.Data;

@Data
public class ClienteComResponse {
    private Long id;
    private String rut;
    private String nombre;
    private String email;
}
