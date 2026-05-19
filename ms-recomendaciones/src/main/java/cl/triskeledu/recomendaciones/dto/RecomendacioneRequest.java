package cl.triskeledu.recomendaciones.dto;

import cl.triskeledu.recomendaciones.model.EstadoRecomendacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RecomendacioneRequest {

    @NotNull(message = "El ID del usuario es obligatorio")
    @Positive(message = "El ID del usuario debe ser válido")
    private Long usuarioId;
    
    // --- Datos de Preferencia ---
    @NotBlank(message = "La categoría de interés no puede estar vacía")
    private String categoriaInteres;
    
    @NotNull(message = "El nivel de interés es obligatorio")
    private Integer nivelInteres;
    
    // --- Datos de Producto Recomendado ---
    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;
    
    @NotNull(message = "El score de relevancia es obligatorio")
    private Double scoreRelevancia;
    
    // --- Datos de la Recomendación general ---
    @NotBlank(message = "La versión del algoritmo es obligatoria")
    private String algoritmoVersion;
    
    @NotNull(message = "El estado de la recomendación es obligatorio")
    private EstadoRecomendacion estado;

}

