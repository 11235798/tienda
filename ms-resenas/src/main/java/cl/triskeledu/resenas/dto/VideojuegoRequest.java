package cl.triskeledu.resenas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideojuegoRequest {

    @NotBlank
    @Size(max = 20)
    private String sku;

    @NotBlank
    @Size(max = 255)
    private String titulo;

    @NotBlank
    @Size(max = 100)
    private String desarrolladora;

    @NotNull
    private Integer anioLanzamiento;
}