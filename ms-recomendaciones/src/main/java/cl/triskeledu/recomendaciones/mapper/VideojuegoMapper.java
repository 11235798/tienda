package cl.triskeledu.recomendaciones.mapper;

import cl.triskeledu.recomendaciones.dto.VideojuegoRequest;
import cl.triskeledu.recomendaciones.dto.VideojuegoResponse;
import cl.triskeledu.recomendaciones.model.Videojuego;

public class VideojuegoMapper {

    private VideojuegoMapper() {
    }

    public static Videojuego toEntity(VideojuegoRequest request) {
        return Videojuego.builder()
                .sku(request.getSku())
                .titulo(request.getTitulo())
                .generoPrincipal(request.getGeneroPrincipal())
                .plataforma(request.getPlataforma())
                .build();
    }

    public static VideojuegoResponse toResponse(Videojuego videojuego) {
        return VideojuegoResponse.builder()
                .id(videojuego.getId())
                .sku(videojuego.getSku())
                .titulo(videojuego.getTitulo())
                .generoPrincipal(videojuego.getGeneroPrincipal())
                .plataforma(videojuego.getPlataforma())
                .build();
    }

}